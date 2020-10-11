package com.whvcse.service.impl;

import com.whvcse.dao.RolePermDao;
import com.whvcse.pojo.RolePerm;
import com.whvcse.service.RolePermService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 彭高浛
 * 2020/6/7/20:11
 */
@Service("rolePermService")
public class RolePermServiceImpl implements RolePermService {

    @Resource
    private RolePermDao rolePermDao;


    @Override
    public Integer addPermissionToRole(List<RolePerm> rolePerms) {
        return rolePermDao.addPermissionToRole(rolePerms);
    }

    @Override
    public Integer deleteHasPermission(Integer r_id) {
        return rolePermDao.deleteHasPermission(r_id);
    }

    @Override
    public List<RolePerm> selectPermissionByEmpId(Integer r_id) {
        return rolePermDao.selectPermissionByEmpId(r_id);
    }
}
