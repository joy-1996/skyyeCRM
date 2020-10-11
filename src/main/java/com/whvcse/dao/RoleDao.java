package com.whvcse.dao;

import com.whvcse.pojo.Role;

import java.util.List;

public interface RoleDao {
    /*查询所有的角色*/
    List<Role> selectAllRoleByPage(Role role);
    /*查询总记录数*/
    Integer selectTotalRecord();
    /*添加角色*/
    boolean addRole(Role role);
    /*修改角色*/
    boolean updateRole(Role role);
    /*修改角色之前的查询*/
    Role selectRoleById(Integer rid);
    /*删除角色*/
    int  deleteRoleById(Integer rid);
    /*查询所有角色*/
    List<Role> selectAllRole();
}
