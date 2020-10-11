package com.whvcse.service;

import com.whvcse.pojo.RoleEmp;

import java.util.List;
import java.util.Set;

public interface   RoleEmpService{
    /*获取角色*/
    Set<String > getRoles(String username);
    /*获取权限*/
    Set<String> getPerms(String username);
    /*给用户分配角色*/
    Integer addRoleToEmployee(List<RoleEmp> roleEmps);
    /*删除用户已有的角色*/
    Integer deleteHasRole(Integer emp_id);
    /*选中已经有的角色*/
    List<RoleEmp> selectRoleByEmpId(Integer emp_id);

}
