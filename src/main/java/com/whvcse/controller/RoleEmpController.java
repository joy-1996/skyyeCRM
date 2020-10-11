package com.whvcse.controller;

import com.whvcse.pojo.RoleEmp;
import com.whvcse.service.RoleEmpService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * @author 彭高浛
 * 2020/6/7/22:30
 */
@Controller
public class RoleEmpController {
    @Resource
    private RoleEmpService roleEmpService;

    @RequestMapping("addRoleToEmployee")
    @RequiresPermissions("/addRoleToEmployee")
    public String addRoleToEmployee(Integer empid, HttpServletRequest request){
        String[] str= request.getParameterValues("rid");
        ArrayList<RoleEmp> list = new ArrayList<>();
        for(int i=0;i<str.length;i++){
            RoleEmp roleEmp = new RoleEmp(Integer.parseInt(str[i]),empid);
            list.add(roleEmp);
        }
        roleEmpService.deleteHasRole(empid);
        Integer result=roleEmpService.addRoleToEmployee(list);
        if(result>0){
            return "employeeAndRole";
        }
        return "addRoleByEmployee";
    }
}
