package com.whvcse.realm;

import com.whvcse.pojo.Employees;
import com.whvcse.service.EmployeesService;
import com.whvcse.service.RoleEmpService;
import com.whvcse.service.RoleService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;
import java.util.Set;


/**
 * @author 彭高浛
 * 2020/6/4/23:47
 */
public class UserRealm extends AuthorizingRealm {

    @Resource
    private EmployeesService employeesService;
    @Resource
    private RoleEmpService roleEmpService;

    public void setEmployeesService(EmployeesService employeesService){
        this.employeesService=employeesService;
    }

    /*授权*/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        /*创建授权信息*/
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        String userName=(String)principals.getPrimaryPrincipal();
        /*通过用户名称插插叙角色并添加*/
        Set<String> set=roleEmpService.getRoles(userName);
        info.setRoles(set);
        /*拿到所有的权限*/
        info.setStringPermissions(roleEmpService.getPerms(userName));
        /*打印一下*/
        System.err.println(info.getRoles());
        System.err.println(info.getStringPermissions());
        return info;
    }

    /*认证*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        SimpleAuthenticationInfo info = null;
        UsernamePasswordToken user=(UsernamePasswordToken) token;
        /*查询当前用户信息*/
        String username = user.getUsername();
        Employees employees=employeesService.empLogin(new Employees(username));

        /*获取session*/
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        session.setAttribute("employees",employees);
        if(employees.getPassword()!=null){
            info = new SimpleAuthenticationInfo(user.getPrincipal(), employees.getPassword(),
                    ByteSource.Util.bytes(user.getPrincipal()), this.getName());
        }else {
            throw new UnknownAccountException();
        }
        return info;
    }
}
