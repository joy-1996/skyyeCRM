package com.whvcse.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author 彭高浛
 * 2020/6/2/17:21
 */

@Controller
public class ForwardController {

    @RequestMapping("access")
    public String access(){
        return "login";
    }

    @RequestMapping("register")
    public String register(){
        return "register";
    }

    @RequestMapping("empManage")
    @RequiresPermissions("/empManage")
    public String empManage(){
        return  "empManage";
    }

    @RequestMapping("customer")
    @RequiresPermissions("/customer")
    public String customer(){
        return  "customer";
    }

    @RequestMapping("customerVisit")
    @RequiresPermissions("/customerVisit")
    public String customerVisit(){
        return "customerVisit";
    }

    @RequestMapping("searchCustomerVisit")
    @RequiresPermissions("/searchCustomerVisit")
    public  String searchCustomerVisit(){
        return "searchCustomerVisit";
    }

    @RequestMapping("customerAndEmployee")
    @RequiresPermissions("/customerAndEmployee")
    public  String customerAndEmployee(){
        return "customerAndEmployee";
    }

    /*如果没有权限则跳回登录页面*/
    @RequestMapping("tologin")
    public String  tologin(){
        return  "login";
    }

    /*====================================================*/
    /*跳转到permission页面*/
    @RequestMapping("permission")
    @RequiresPermissions("/permission")
    public String permission(){
        return  "permission";
    }

    /*跳转到role页面*/
    @RequestMapping("role")
    @RequiresPermissions("/role")
    public String role(){
        return  "role";
    }

    /*主管查看所有客户信息*/
    @RequestMapping("customerByAdmin")
    @RequiresPermissions("/customerByAdmin")
    public String customerByAdmin(){
        return "customerByAdmin";
    }


    @RequestMapping("employeeAndRole")
    @RequiresPermissions("/employeeAndRole")
    public String employeeAndRole(){
        return "employeeAndRole";
    }

    @RequestMapping("loginout")
    public String loginout(){
        return "login";
    }

}
