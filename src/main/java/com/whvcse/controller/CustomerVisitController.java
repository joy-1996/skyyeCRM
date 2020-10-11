package com.whvcse.controller;

import com.whvcse.pojo.Customer;
import com.whvcse.pojo.Customervisit;
import com.whvcse.pojo.Employees;
import com.whvcse.service.CustomerService;
import com.whvcse.service.CustomerVisitService;
import com.whvcse.service.EmployeesService;
import com.whvcse.utils.BootstrapPage;
import com.whvcse.utils.BootstrapPage2;
import com.whvcse.utils.Page;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
 * 2020/6/3/14:10
 */

@Controller
public class CustomerVisitController {
    @Resource
    private EmployeesService employeesService;
    @Resource
    private CustomerVisitService customerVisitService;
    @Resource
    private CustomerService customerService;

    /*查询所有的员工*/
    @RequestMapping("searchEmployeeByPage")
    @RequiresPermissions("/searchEmployeeByPage")
    public void searchEmployeeByPage(HttpServletResponse response, HttpServletRequest request){
        Integer offset = Integer.parseInt(request.getParameter("offset"));
        Integer pageSize = Integer.parseInt(request.getParameter("limit"));
        int currentPage=offset/pageSize+1;
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
    /*分页查询用户对应的所有客户*/
    @RequestMapping("searchAllCustomersVisitByPage")
    @RequiresPermissions("/searchAllCustomersVisitByPage")
    public void searchAllCustomersVisitByPage(Customervisit customervisit,HttpServletResponse response, HttpServletRequest request){
        Integer offset = Integer.parseInt(request.getParameter("offset"));
        Integer pageSize = Integer.parseInt(request.getParameter("limit"));
        int currentPage=offset/pageSize+1;
        Page<Customervisit> page=new Page<>(currentPage,pageSize);
        HashMap<String,Integer> map=new HashMap<>();
        map.put("startIndex", (page.getCurrentPage()-1)*page.getPageSize());
        map.put("pageSize", page.getPageSize());
        customervisit.setMap(map);
        List<Customervisit> list= customerVisitService.searchAllCustomersVisitByPage(customervisit);
        int totalRecord= customerVisitService.selectTotalRecord(customervisit.getEmpid());
        BootstrapPage<Customervisit> bPage=new BootstrapPage<>();
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
    /*添加拜访客户信息*/
    @RequestMapping("insertCustomerVisit")
    @RequiresPermissions("/insertCustomerVisit")
    public void insertCustomerVisit(Customervisit customervisit,HttpServletResponse response){
       Integer result= customerVisitService.insertCustomerVisit(customervisit);
       if(result>0){
           try {
               response.getWriter().print("success");
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
    }
    /*给下拉框添加数据*/
    @RequestMapping("selectCustomerAndEmployee")
    @RequiresPermissions("/selectCustomerAndEmployee")
    public void selectCustomerAndEmployee(Integer empid,HttpServletResponse response){
        List<Customer> cusList= customerService.selectAllCustomers();
        List<Employees> empList= employeesService.selectAllEmployees();
        Employees empList2= employeesService.selectEmployeeById(empid);
        System.out.println(empList2);
        Object[] array={cusList,empList,empList2};
        JSONArray json=JSONArray.fromObject(array);
        try {
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(json.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*修改拜访记录前的准备*/
    @RequestMapping("preUpdateCustomerVisit")
    @RequiresPermissions("/preUpdateCustomerVisit")
    public void preUpdateCustomerVisit(Integer id,HttpServletResponse response,HttpServletRequest request){
        Customervisit customerVisit=  customerVisitService.preUpdateCustomerVisit(id);
        if(customerVisit!=null){
            JSONObject json=JSONObject.fromObject(customerVisit);
            try {
                response.setCharacterEncoding("utf-8");
                response.getWriter().print(json.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /*选中一个用户*/
    @RequestMapping("customerVisitDetail")
    @RequiresPermissions("/customerVisitDetail")
    public  String customerVisitDetail(Integer empid, HttpServletRequest request) {
        Employees employees= employeesService.selectEmployeeById(empid);
        System.out.println(employees);
        request.setAttribute("employees",employees);
        return "customerVisitDetail";
    }
    /*删除拜访记录*/
    @RequestMapping("deleteCustomerVisitById")
    @RequiresPermissions("/deleteCustomerVisitById")
    public void deleteCustomerVisitById(Integer id,HttpServletResponse response){
       Integer result=  customerVisitService.deleteCustomerVisitById(id);
       if(result>0){
           try {
               response.getWriter().print("success");
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
    }
    /*修改拜访记录*/
    @RequestMapping("updateCustomerVisit")
    @RequiresPermissions("/updateCustomerVisit")
    public void updateCustomerVisit(Customervisit customervisit,HttpServletResponse response){
        Integer result=  customerVisitService.updateCustomerVisit(customervisit);
        if(result>0){
            try {

                response.getWriter().print("success");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /*查询返回当前用户信息*/
    @RequestMapping("searchOneEmployee")
    @RequiresPermissions("/searchOneEmployee")
    public void searchOneEmployee(Integer empid,HttpServletRequest request,HttpServletResponse response){
        Integer offset = Integer.parseInt(request.getParameter("offset"));
        Integer pageSize = Integer.parseInt(request.getParameter("limit"));
        int currentPage=offset/pageSize+1;
        Page<Employees> page=new Page<>(currentPage,pageSize);
        HashMap<String,Integer> map=new HashMap<>();
        map.put("startIndex", (page.getCurrentPage()-1)*page.getPageSize());
        map.put("pageSize", page.getPageSize());
        Employees employees=new Employees();
        employees.setMap(map);
        Employees employee= employeesService.selectEmployeeById(empid);
        int totalRecord= employeesService.selectOneTotalRecord(empid);
        BootstrapPage2<Employees> bPage=new BootstrapPage2<>();
        bPage.setRows(employee);
        System.out.println("bPage.getRows() = " + bPage.getRows());
        bPage.setTotal(totalRecord);
        if(employee!=null){
            JSONObject json=JSONObject.fromObject(bPage);
            try {
                response.setCharacterEncoding("utf-8");
                response.getWriter().print(json.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

