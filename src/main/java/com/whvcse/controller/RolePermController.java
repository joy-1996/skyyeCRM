package com.whvcse.controller;

import com.whvcse.pojo.RoleEmp;
import com.whvcse.pojo.RolePerm;
import com.whvcse.service.RolePermService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * @author 彭高浛
 * 2020/6/8/2:18
 */
@Controller
public class RolePermController {
    @Resource
    private RolePermService rolePermService;

    @RequestMapping("addPermissionByRole")
    @RequiresPermissions("/addPermissionByRole")
    public String addPermissionByRole(Integer rid, HttpServletRequest request){
        String[] str= request.getParameterValues("pid");
        ArrayList<RolePerm> list = new ArrayList<>();
        for(int i=0;i<str.length;i++){
            System.err.println("str="+str[i]);
            RolePerm rolePerm = new RolePerm(rid,Integer.parseInt(str[i]));
            list.add(rolePerm);
        }
        rolePermService.deleteHasPermission(rid);
        Integer result=rolePermService.addPermissionToRole(list);
        if(result>0){
            return "role";
        }
        return "addPermissionToRole";
    }
}
