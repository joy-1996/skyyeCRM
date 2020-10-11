package com.whvcse.controller;

import com.whvcse.pojo.Role;
import com.whvcse.pojo.RoleEmp;
import com.whvcse.service.RoleEmpService;
import com.whvcse.service.RoleService;
import com.whvcse.utils.BootstrapPage;
import com.whvcse.utils.Page;
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

@Controller
public class RoleController {
    @Resource
    private RoleService roleService;
    @Resource
    private RoleEmpService roleEmpService;

    @RequestMapping("selectAllRoleByPage")
    @RequiresPermissions("/selectAllRoleByPage")
    public void selectAllRoleByPage(HttpServletRequest request, HttpServletResponse response){
        Integer offset = Integer.parseInt(request.getParameter("offset"));
        Integer pageSize = Integer.parseInt(request.getParameter("limit"));
        int currentPage=offset/pageSize+1;
        Page<Role> page=new Page<>(currentPage,pageSize);
        HashMap<String,Integer> map=new HashMap<>();
        map.put("startIndex", (page.getCurrentPage()-1)*page.getPageSize());
        map.put("pageSize", page.getPageSize());
        Role role=new Role();
        role.setMap(map);
        List<Role> list= roleService.selectAllRoleByPage(role);
        int totalRecord= roleService.selectTotalRecord();
        BootstrapPage<Role> bPage=new BootstrapPage<>();
        bPage.setRows(list);
        bPage.setTotal(totalRecord);
        JSONObject json=JSONObject.fromObject(bPage);
        try {
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(json.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*添加角色*/
    @RequestMapping("addRole")
    @RequiresPermissions("/addRole")
    public void addRole(String rolename, HttpServletResponse response){
        boolean result= roleService.addRole(new Role(rolename));
        if(result){
            try {
                response.getWriter().print("success");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /*查询权限（修改权限之前的准备工作）*/
    @RequestMapping("selectRoleById")
    @RequiresPermissions("/selectRoleById")
    public void selectRoleById(Integer rid, HttpServletResponse response){
        Role role= roleService.selectRoleById(rid);
        JSONObject json=JSONObject.fromObject(role);
        try {
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(json.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*修改权限*/
    @RequestMapping("updateRole")
    @RequiresPermissions("/updateRole")
    public void updateRole(Role role, HttpServletResponse response){
        boolean result=roleService.updateRole(role);
        if(result){
            response.setCharacterEncoding("utf-8");
            try {
                response.getWriter().print("success");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /*删除权限*/
    @RequestMapping("deleteRoleById")
    @RequiresPermissions("/deleteRoleById")
    public void deleteRoleById(Integer rid, HttpServletResponse response){
        Integer result= roleService.deleteRoleById(rid);
        System.out.println(result);
        if(result>0){
            try {
                response.setCharacterEncoding("utf-8");
                response.getWriter().print("success");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /*进入分配角色页面*/
    @RequestMapping("addRoleByEmployee")
    @RequiresPermissions("/addRoleByEmployee")
    public String addRoleByEmployee(Integer empid,HttpServletRequest request){
       List<Role> roleList= roleService.selectAllRole();
       List<RoleEmp> roleEmpList= roleEmpService.selectRoleByEmpId(empid);
        System.err.println(roleEmpList);
        System.err.println(roleList);
        request.setAttribute("roleEmpList",roleEmpList);
        request.setAttribute("reList",roleList);
        request.setAttribute("empid",empid);
        return "addRoleByEmployee";
    }




}
