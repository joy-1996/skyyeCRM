package com.whvcse.service;

import com.whvcse.pojo.Employees;

import java.util.List;

/**
 * @author 彭高浛
 * 2020/6/2/16:40
 */
public interface EmployeesService {

    /*用户登录*/
    Employees empLogin(Employees employees);

    /*注册用户*/
    Integer insertEmployee(Employees employee);

    /*修改密码*/
    Integer updatePassword(Employees employees);

    /*查询所有的员工*/
    List<Employees> searchAllEmployeesByPage(Employees employees);

    /*总记录数*/
    Integer selectTotalRecord();

    /*删除用户*/
    Integer deleteEmployeeById(Integer empid);

    /*修改前的准备*/
    Employees selectEmployeeById(Integer empid);

    /*修改用户信息*/
    Integer updateEmployee(Employees employees);

    /*查询所有用户信息*/
    List<Employees> selectAllEmployees();
    /*查询单个记录*/
    Integer selectOneTotalRecord(Integer empid);

    /*<!--查询所有用户不包含本用户-->*/
    List<Employees> selectNotInEmp(Employees employees);
    /*获取角色*/
    List<String> getRoles();
    /*获取权限*/
    List<String> getPerms();

}
