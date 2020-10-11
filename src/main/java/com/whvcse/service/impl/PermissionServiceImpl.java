package com.whvcse.service.impl;

import com.whvcse.dao.PermissionDao;
import com.whvcse.pojo.Permission;
import com.whvcse.service.PermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {
    @Resource
    private PermissionDao permissionDao;
    /*分页查询所有权限*/
    @Override
    public List<Permission> selectAllPermissionByPage(Permission permission) {
        return permissionDao.selectAllPermissionByPage(permission);
    }

    /*总记录数*/
    @Override
    public Integer selectTotalRecord() {
        return permissionDao.selectTotalRecord();
    }
    /*添加权限*/
    @Override
    public boolean addPermission(Permission permission) {
        return permissionDao.addPermission(permission);
    }
    /*修改权限*/
    @Override
    public boolean updatePermission(Permission permission) {
        return permissionDao.updatePermission(permission);
    }

    @Override
    public Permission selectPermissionById(Integer pid) {
        return permissionDao.selectPermissionById(pid);
    }
    /*删除权限*/
    @Override
    public Integer deletePermissionById(Integer pid) {
        return permissionDao.deletePermissionById(pid);
    }
    /*查询所有权限*/
    @Override
    public List<Permission> selectAllPermission() {
        return permissionDao.selectAllPermission();
    }
}
