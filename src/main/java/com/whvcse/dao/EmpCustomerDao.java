package com.whvcse.dao;

import com.whvcse.pojo.EmpCustomer;

import java.util.List;

public interface EmpCustomerDao {
    /*查询用户对应的所有客户*/
    List<EmpCustomer> searchCusEmployee(EmpCustomer empCustomer);
    /*总记录数*/
    Integer selectTotalRecord(Integer empid);
  /* *//*转移客户*//*
    Integer  transferEmployee(EmpCustomer empCustomer);*/
}
