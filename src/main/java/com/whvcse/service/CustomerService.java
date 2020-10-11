package com.whvcse.service;

import com.whvcse.pojo.Customer;

import java.util.List;

public interface CustomerService {

    /*分页查询所有的客户*/
    List<Customer> searchAllCustomersByPage(Customer customer);
    /*总记录数*/
    Integer selectTotalRecord();
    /*添加客户*/
    Integer insertCustomer(Customer customer);
    /*查询客户信息*/
    Customer searchCustomerById(Integer cid);
    /*删除客户信息*/
    Integer deleteCustomerById(Integer cid);
    /*修改客户信息*/
    Integer updateCustomer(Customer customer);
    /*查询所有客户信息*/
    List<Customer> selectAllCustomers();
    /*分页查询用户所有的客户*/
    List<Customer> searchAllCustomersByPageAndId(Customer customer);
    /*分页查询用户所有的客户总记录数*/
    Integer selectTotalRecordByPageAndId(Integer eid);
    /*转移客户*/
    Integer  transferEmployee(Customer customer);
}

