package com.whvcse.controller;


import com.whvcse.pojo.Permission;
import com.whvcse.pojo.RolePerm;
import com.whvcse.service.PermissionService;
import com.whvcse.service.RolePermService;
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
public class PermissionController {
    @Resource
    private PermissionService permissionService;
    @Resource
    private RolePermService rolePermService;

    @RequestMapping("selectAllPermissionByPage")
    @RequiresPermissions("/selectAllPermissionByPage")
    public void selectAllPermissionByPage(HttpServletRequest request, HttpServletResponse response){
        Integer offset = Integer.parseInt(request.getParameter("offset"));
        Integer pageSize = Integer.parseInt(request.getParameter("limit"));
        System.out.println(""+offset+"  "+pageSize);
        int currentPage=offset/pageSize+1;
        Page<Permission> page=new Page<>(currentPage,pageSize);
        HashMap<String,Integer> map=new HashMap<>();
        map.put("startIndex", (page.getCurrentPage()-1)*page.getPageSize());
        map.put("pageSize", page.getPageSize());
        Permission permission=new Permission();
        permission.setMap(map);
        List<Permission> list= permissionService.selectAllPermissionByPage(permission);
        System.err.println(list);
        int totalRecord= permissionService.selectTotalRecord();
        BootstrapPage<Permission> bPage=new BootstrapPage<>();
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

    @RequestMapping("addPermission")
    @RequiresPermissions("/addPermission")
    public void addPermission(Permission permission, HttpServletResponse response){
        boolean result= permissionService.addPermission(permission);
        if(result){
            try {
                response.getWriter().print("success");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /*查询权限（修改权限之前的准备工作）*/
    @RequestMapping("selectPermissionById")
    @RequiresPermissions("/selectPermissionById")
    public void selectPermissionById(Integer pid, HttpServletResponse response){
        Permission permission= permissionService.selectPermissionById(pid);
        JSONObject json=JSONObject.fromObject(permission);
        try {
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(json.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        /*修改权限*/
    @RequestMapping("updatePermission")
    @RequiresPermissions("/updatePermission")
    public void updatePermission(Permission permission, HttpServletResponse response){
        boolean result=permissionService.updatePermission(permission);
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
    @RequestMapping("deletePermissionById")
    @RequiresPermissions("/deletePermissionById")
    public void deletePermission(Integer pid, HttpServletResponse response){
        Integer result= permissionService.deletePermissionById(pid);
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
    @RequestMapping("addPermissionToRole")
    @RequiresPermissions("/addPermissionToRole")
    public String addPermissionToRole(Integer rid, HttpServletRequest request){
        List<Permission> list= permissionService.selectAllPermission();
        List<RolePerm> rpList= rolePermService.selectPermissionByEmpId(rid);
        request.setAttribute("perList",list);
        request.setAttribute("rpList",rpList);
        request.setAttribute("rid",rid);
        System.err.println(list);
        System.err.println(rid);
        System.err.println(rpList);
        return "addPermissionToRole";
    }
}
