package com.whvcse.service.impl;

import com.whvcse.dao.RoleDao;
import com.whvcse.pojo.Role;
import com.whvcse.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("roleService")
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleDao roleDao;

    /*查询所有的角色*/
    @Override
    public List<Role> selectAllRoleByPage(Role role) {
        return roleDao.selectAllRoleByPage(role);
    }

    /*查询总记录数*/
    @Override
    public Integer selectTotalRecord() {
        return roleDao.selectTotalRecord();
    }
    /*添加角色*/
    @Override
    public boolean addRole(Role role) {
        return roleDao.addRole(role);
    }
    /*修改角色*/
    @Override
    public boolean updateRole(Role role) {
        return roleDao.updateRole(role);
    }
    /*查询角色*/
    @Override
    public Role selectRoleById(Integer rid) {
        return roleDao.selectRoleById(rid);
    }
    /*删除角色*/
    @Override
    public int deleteRoleById(Integer rid) {
        return roleDao.deleteRoleById(rid);
    }

    @Override
    public List<Role> selectAllRole() {
        return roleDao.selectAllRole();
    }

}
