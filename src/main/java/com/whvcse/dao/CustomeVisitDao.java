package com.whvcse.dao;

import com.whvcse.pojo.Customervisit;

import java.util.List;

public interface CustomeVisitDao {
    /*分页查询用户对应的所有客户*/
    List<Customervisit> searchAllCustomersVisitByPage(Customervisit customervisit);
    /*总记录数*/
    Integer selectTotalRecord(Integer empid);
    /*添加客户拜访信息*/
    Integer insertCustomerVisit(Customervisit customervisit);
    /*修改拜访记录之前的准备*/
    Customervisit preUpdateCustomerVisit(Integer id);
    /*删除拜访记录*/
    Integer deleteCustomerVisitById(Integer id);
    /*修改拜访记录*/
    Integer updateCustomerVisit(Customervisit customervisit);
}
