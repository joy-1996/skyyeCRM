package com.whvcse.service.impl;

import com.whvcse.dao.CustomerDao;
import com.whvcse.pojo.Customer;
import com.whvcse.service.CustomerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 彭高浛
 * 2020/6/3/11:24
 */
@Service("customerService")
public class CustomerServiceImpl implements CustomerService {
    @Resource
    private CustomerDao customerDao;
    /*分页查询*/
    @Override
    public List<Customer> searchAllCustomersByPage(Customer customer) {
        return customerDao.searchAllCustomersByPage(customer);
    }

    /*总记录数*/
    @Override
    public Integer selectTotalRecord() {
        return customerDao.selectTotalRecord();
    }

    /*添加客户*/
    @Override
    public Integer insertCustomer(Customer customer){
        return  customerDao.insertCustomer(customer);
    }

    /*查询客户信息*/
    @Override
    public Customer searchCustomerById(Integer cid) {
        return customerDao.searchCustomerById(cid);
    }

    /*删除客户信息*/
    @Override
    public Integer deleteCustomerById(Integer cid) {
        return customerDao.deleteCustomerById(cid);
    }
    /*修改客户信息*/
    @Override
    public Integer updateCustomer(Customer customer){
        return  customerDao.updateCustomer(customer);
    }

    /*查询所有客户信息*/
    @Override
    public List<Customer> selectAllCustomers() {
        return customerDao.selectAllCustomers();
    }
    /*分页查询用户所有的客户*/
    @Override
    public List<Customer> searchAllCustomersByPageAndId(Customer customer) {
        return customerDao.searchAllCustomersByPageAndId(customer);
    }

    /*分页查询用户所有的客户总记录数*/
    @Override
    public Integer selectTotalRecordByPageAndId(Integer eid) {
        return customerDao.selectTotalRecordByPageAndId(eid);
    }

    @Override
    public Integer transferEmployee(Customer customer) {
        return customerDao.transferEmployee(customer);
    }
}
