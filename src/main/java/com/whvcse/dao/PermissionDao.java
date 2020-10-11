package com.whvcse.dao;

import com.whvcse.pojo.Permission;

import java.util.List;

public interface PermissionDao {
    /*分页查找*/
    List<Permission> selectAllPermissionByPage(Permission permission);
    /*查找总记录数*/
    Integer selectTotalRecord();
    /*添加权限*/
    boolean addPermission(Permission permission);
    /*修改权限*/
    boolean updatePermission(Permission permission);
    /*修改权限之前的准备工作*/
    Permission selectPermissionById(Integer pid);
    /*删除权限*/
    Integer deletePermissionById(Integer pid);
    /*查询所有权限*/
    List<Permission> selectAllPermission();
}
