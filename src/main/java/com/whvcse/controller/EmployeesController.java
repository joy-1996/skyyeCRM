package com.whvcse.controller;

import com.github.pagehelper.PageHelper;
import com.whvcse.pojo.Employees;
import com.whvcse.service.EmployeesService;
import com.whvcse.utils.BootstrapPage;
import com.whvcse.utils.Page;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * @author 彭高浛
 * 2020/6/2/17:10
 */

@Controller
public class EmployeesController  {
    @Resource
    private EmployeesService employeesService;

    /*登录*/
    @RequestMapping("login")
    public String login(Employees employees, HttpServletRequest request){

        String username = employees.getUsername();
        String password = employees.getPassword();
        String msg = null;
        // shiro代码
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            msg = "账号密码错误";
        } catch (IncorrectCredentialsException e) {
            msg = "账号密码错误";
        } catch (Exception e) {
            msg = "其他错误";
        }
        if (null != msg) {
            request.setAttribute("msg",msg);
           return "login";
        }
        request.setAttribute("msg", msg);
        return "main" ;
    }

    /*注册用户*/
    @RequestMapping("insertEmployee")
    public String insertEmployee(Employees employees){
        if(employees!=null){
            Md5Hash hash = new Md5Hash(employees.getPassword(), employees.getUsername(), 2);
            String password=""+hash;
            employees.setPassword(password);
            Integer result= employeesService.insertEmployee(employees);
            if(result>0){
                return "login";
            }
        }
        return "register";
    }

    /*修改密码*/
    @RequestMapping("updatePassword")
    public void updatePassword(Employees employees, HttpServletResponse response){
        System.err.println(employees.getPassword()+employees.getUsername());
        Md5Hash hash = new Md5Hash(employees.getPassword(), employees.getUsername(), 2);
        String password=""+hash;
        employees.setPassword(password);
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        Integer result= employeesService.updatePassword(employees);
        if (result>0){
            Object employees1 = session.getAttribute("employees");
            session.removeAttribute(employees1);
            try {
                response.getWriter().print("success");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*合作专员管理*/
    @RequestMapping("searchAllEmployeesByPage")
    @RequiresPermissions("/searchAllEmployeesByPage")
    public void searchAllEmployeesByPage(HttpServletResponse response,HttpServletRequest request){
        Integer offset = Integer.parseInt(request.getParameter("offset"));
        Integer pageSize = Integer.parseInt(request.getParameter("limit"));
        int currentPage=offset/pageSize+1;
    /*    Page<Employees> page = PageHelper.startPage(currentPage,pageSize);*/
        Page<Employees> page=new Page<>(currentPage,pageSize);
        HashMap<String,Integer> map=new HashMap<>();
        map.put("startIndex", (page.getCurrentPage()-1)*page.getPageSize());
        map.put("pageSize", page.getPageSize());
        Employees employees=new Employees();
        employees.setMap(map);
        List<Employees> list= employeesService.searchAllEmployeesByPage(employees);
        int totalRecord= employeesService.selectTotalRecord();
        BootstrapPage<Employees> bPage=new BootstrapPage<>();
        bPage.setRows(list);
        bPage.setTotal(totalRecord);
         if(list!=null){
             JSONObject json=JSONObject.fromObject(bPage);
             try {
                 response.setCharacterEncoding("utf-8");
                 response.getWriter().print(json.toString());
             } catch (IOException e) {
                e.printStackTrace();
             }
         }
    }

    @RequestMapping("addEmployee")
    @RequiresPermissions("/addEmployee")
    public void addEmployee(Employees employees,HttpServletResponse response) throws IOException {
        if(employees!=null){
            Md5Hash hash = new Md5Hash(employees.getPassword(), employees.getUsername(), 2);
            String password=""+hash;
            employees.setPassword(password);
            Integer result= employeesService.insertEmployee(employees);
            if(result>0){
                response.getWriter().print("success");
            }
        }
    }

    /*删除用户*/
    @RequestMapping("deleteEmployeeById")
    @RequiresPermissions("/deleteEmployeeById")
    public void deleteEmployeeById(Integer empid,HttpServletResponse response){
       Integer result= employeesService.deleteEmployeeById(empid);
       if(result>0) {
           try {
               response.getWriter().print("success");
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
    }

    @RequestMapping("preUpdateEmployee")
    @RequiresPermissions("/preUpdateEmployee")
    public void preUpdateEmployee(Integer empid,HttpServletResponse response){
      Employees employee= employeesService.selectEmployeeById(empid);
      if(employee!=null){
          JSONObject json=JSONObject.fromObject(employee);
          try {
              response.setCharacterEncoding("utf-8");
              response.getWriter().print(json.toString());
          } catch (IOException e) {
              e.printStackTrace();
          }
      }
    }

    @RequestMapping("updateEmployee")
    @RequiresPermissions("/updateEmployee")
    public void updateEmployee(Employees employee,HttpServletResponse response){
      Integer result=   employeesService.updateEmployee(employee);
      
      if(result>0){
          try {
              response.getWriter().print("success");
          } catch (IOException e) {
              e.printStackTrace();
          }
      }
    }

    /*<!--查询所有用户不包含本用户-->*/
    @RequestMapping("selectNotInEmp")
    public void selectNotInEmp(Employees employees,HttpServletResponse response){
        List<Employees> list= employeesService.selectNotInEmp(employees);
        for (Employees list2:list) {
            System.out.println("list2 = " + list2);
        }
        if(list!=null){
            JSONArray json=JSONArray.fromObject(list);
            try {
                response.setCharacterEncoding("utf-8");
                response.getWriter().print(json.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
