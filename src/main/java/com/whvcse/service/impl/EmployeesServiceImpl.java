package com.whvcse.service.impl;

import com.whvcse.dao.EmployeesDao;
import com.whvcse.dao.PermissionDao;
import com.whvcse.dao.RoleDao;
import com.whvcse.pojo.Employees;
import com.whvcse.pojo.Permission;
import com.whvcse.pojo.Role;
import com.whvcse.service.EmployeesService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author 彭高浛
 * 2020/6/2/17:07
 */
@Transactional
@Service("employeesService")
public class EmployeesServiceImpl implements EmployeesService {
    @Resource
    private EmployeesDao employeesDao;
    @Resource
    private PermissionDao permissionDao;
    @Resource
    private RoleDao roleDao;
    /*用户登录*/
    @Override
    public Employees empLogin(Employees employees) {
        return employeesDao.empLogin(employees);
    }


    /*注册用户*/
    @Override
    public Integer insertEmployee(Employees employee) {

        return employeesDao.insertEmployee(employee);
    }

    /*修改密码*/
    @Override
    public Integer updatePassword(Employees employees) {
        return employeesDao.updatePassword(employees);
    }

    /*查询所有的员工*/
    @Override
    public List<Employees> searchAllEmployeesByPage(Employees employees) {
        return employeesDao.searchAllEmployeesByPage(employees);
    }
    /*总记录数*/
    @Override
    public Integer selectTotalRecord() {
        return employeesDao.selectTotalRecord();
    }

    /*删除用户*/
    @Override
    public Integer deleteEmployeeById(Integer empid) {
        return employeesDao.deleteEmployeeById(empid);
    }

    /*修改前的准备*/
    @Override
    public Employees selectEmployeeById(Integer empid) {
        return employeesDao.selectEmployeeById(empid);
    }

    /*修改用户信息*/
    @Override
    public Integer updateEmployee(Employees employees) {
        return employeesDao.updateEmployee(employees);
    }

    /*查询所有用户信息*/
    @Override
    public List<Employees> selectAllEmployees() {
        return employeesDao.selectAllEmployees();
    }

    @Override
    public Integer selectOneTotalRecord(Integer empid) {
        return employeesDao.selectOneTotalRecord(empid);
    }

    /*<!--查询所有用户不包含本用户-->*/
    @Override
    public List<Employees> selectNotInEmp(Employees employees) {
        return employeesDao.selectNotInEmp(employees);
    }
    /*获取角色*/

    @Override
    public List<String > getRoles(){
        List<String> list=new ArrayList<>();
        List<Role> roles=roleDao.selectAllRole();
        for (int i=0;i<roles.size();i++){
            list.add(roles.get(i).getRolename());
        }
        System.err.println(list);
        return  list;
    }

    @Override
    public List<String> getPerms() {
       List<String> list=new ArrayList<>();
        List<Permission>  permissions= permissionDao.selectAllPermission();
       for (int i=0;i<permissions.size();i++){
            list.add(permissions.get(i).getPermissionurl());
       }
        System.err.println(list);
        return list;
    }
}
