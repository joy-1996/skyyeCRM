package com.whvcse.service.impl;

import com.whvcse.dao.EmpCustomerDao;
import com.whvcse.pojo.EmpCustomer;
import com.whvcse.service.EmpCustomerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 彭高浛
 * 2020/6/4/14:06
 */
@Service("empCustomerService")
public class EmpCustomerServiceImpl implements EmpCustomerService {
    @Resource
    private EmpCustomerDao empCustomerDao;

    @Override
    public List<EmpCustomer> searchCusEmployee(EmpCustomer empCustomer) {
        return empCustomerDao.searchCusEmployee(empCustomer);
    }

    /*总记录数*/
    @Override
    public Integer selectTotalRecord(Integer empid) {
        return empCustomerDao.selectTotalRecord(empid);
    }

    /*转移客户*//*
    @Override
    public Integer transferEmployee(EmpCustomer empCustomer) {
        return empCustomerDao.transferEmployee(empCustomer);
    }*/

}
