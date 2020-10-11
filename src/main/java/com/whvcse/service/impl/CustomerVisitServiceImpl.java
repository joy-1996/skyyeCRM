package com.whvcse.service.impl;

import com.whvcse.dao.CustomeVisitDao;
import com.whvcse.pojo.Customervisit;
import com.whvcse.service.CustomerVisitService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 彭高浛
 * 2020/6/3/15:38
 */
@Service("customerVisitService")
public class CustomerVisitServiceImpl implements CustomerVisitService {
    @Resource
    private CustomeVisitDao customeVisitDao;

    /*分页查询用户对应的所有客户*/
    @Override
    public List<Customervisit> searchAllCustomersVisitByPage(Customervisit customervisit) {
        return customeVisitDao.searchAllCustomersVisitByPage(customervisit);
    }

    /*总记录数*/
    @Override
    public Integer selectTotalRecord(Integer empid) {
        return customeVisitDao.selectTotalRecord(empid);
    }
    /*添加客户拜访信息*/
    @Override
    public Integer insertCustomerVisit(Customervisit customervisit) {
        return customeVisitDao.insertCustomerVisit(customervisit);
    }

    /*修改拜访记录之前的准备*/
    @Override
    public Customervisit preUpdateCustomerVisit(Integer id) {
        return customeVisitDao.preUpdateCustomerVisit(id);
    }

    /*删除拜访记录*/
    @Override
    public Integer deleteCustomerVisitById(Integer id) {
        return customeVisitDao.deleteCustomerVisitById(id);
    }

    /*修改拜访记录*/
    @Override
    public Integer updateCustomerVisit(Customervisit customervisit) {
        return customeVisitDao.updateCustomerVisit(customervisit);
    }
}
