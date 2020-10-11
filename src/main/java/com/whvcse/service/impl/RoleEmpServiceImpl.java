package com.whvcse.service.impl;

import com.whvcse.dao.RoleEmpDao;
import com.whvcse.pojo.RoleEmp;
import com.whvcse.service.RoleEmpService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * @author 彭高浛
 * 2020/6/6/16:27
 */

@Service("roleEmpService")
public class RoleEmpServiceImpl implements RoleEmpService {
    @Resource
    private RoleEmpDao roleEmpDao;

    @Override
    public Set<String> getRoles(String username) {
        return roleEmpDao.getRoles(username);
    }

    @Override
    public Set<String> getPerms(String username) {
        return roleEmpDao.getPerms(username);
    }

    /*给用户分配角色*/
    @Override
    public Integer addRoleToEmployee(List<RoleEmp> roleEmps) {
        return roleEmpDao.addRoleToEmployee(roleEmps);
    }
    /*删除用户已有的角色*/
    @Override
    public Integer deleteHasRole(Integer emp_id) {
        return roleEmpDao.deleteHasRole(emp_id);
    }

    /*选中已经有的角色*/
    @Override
    public List<RoleEmp> selectRoleByEmpId(Integer emp_id) {
        return roleEmpDao.selectRoleByEmpId(emp_id);
    }


}
