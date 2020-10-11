package com.whvcse.controller;

import com.whvcse.pojo.Customer;
import com.whvcse.service.CustomerService;
import com.whvcse.service.EmployeesService;
import com.whvcse.utils.BootstrapPage;
import com.whvcse.utils.Page;
import net.sf.json.JSONObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * @author 彭高浛
 * 2020/6/3/11:27
 */

@Controller
public class CustomerController {

    @Resource
    private CustomerService customerService;

    @Resource
    private EmployeesService employeesService;

    /**
     *
     * @param response
     * @param request
     */
    @RequestMapping("searchAllCustomersByPage")
    @RequiresPermissions("/searchAllCustomersByPage")
    public void searchAllCustomersByPage(HttpServletResponse response, HttpServletRequest request){
        Integer offset = Integer.parseInt(request.getParameter("offset"));
        Integer pageSize = Integer.parseInt(request.getParameter("limit"));
        int currentPage=offset/pageSize+1;
        Page<Customer> page=new Page<>(currentPage,pageSize);
        HashMap<String,Integer> map=new HashMap<>(40);
        map.put("startIndex", (page.getCurrentPage()-1)*page.getPageSize());
        map.put("pageSize", page.getPageSize());
        Customer customer=new Customer();
        customer.setMap(map);
        List<Customer> list= customerService.searchAllCustomersByPage(customer);
        int totalRecord= customerService.selectTotalRecord();
        BootstrapPage<Customer> bPage=new BootstrapPage<>();
        bPage.setRows(list);
        bPage.setTotal(totalRecord);
        if(list!=null){
            JSONObject json=JSONObject.fromObject(bPage);
            try {
                response.setCharacterEncoding("utf-8");
                response.getWriter().print(json.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *
     * @param eid
     * @param response
     * @param request
     */
    @RequestMapping("searchAllCustomersByPageAndId")
    @RequiresPermissions("/searchAllCustomersByPageAndId")
    public void searchAllCustomersByPageAndId(Integer eid,HttpServletResponse response, HttpServletRequest request){
        Integer offset = Integer.parseInt(request.getParameter("offset"));
        Integer pageSize = Integer.parseInt(request.getParameter("limit"));
        int currentPage=offset/pageSize+1;
        Page<Customer> page=new Page<>(currentPage,pageSize);
        HashMap<String,Integer> map=new HashMap<>(68);
        map.put("startIndex", (page.getCurrentPage()-1)*page.getPageSize());
        map.put("pageSize", page.getPageSize());
        Customer customer=new Customer();
        customer.setEid(eid);
        customer.setMap(map);
        List<Customer> list= customerService.searchAllCustomersByPageAndId(customer);
        int totalRecord= customerService.selectTotalRecordByPageAndId(eid);
        BootstrapPage<Customer> bPage=new BootstrapPage<>();
        bPage.setRows(list);
        bPage.setTotal(totalRecord);
        if(list!=null){
            JSONObject json=JSONObject.fromObject(bPage);
            try {
                response.setCharacterEncoding("utf-8");
                response.getWriter().print(json.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
       }
    /**
     *
     * @param customer
     * @param response
     */
    @RequestMapping("addCustomer")
    @RequiresPermissions("/addCustomer")
    public void addCustomer(Customer customer,HttpServletResponse response){
        Integer result=customerService.insertCustomer(customer);
        if(result>0){
            try {
                response.getWriter().print("success");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*修改客户信息侧准备*/

    /**
     *
     * @param cid
     * @param response
     */
    @RequestMapping("preUpdateCustomer")
    @RequiresPermissions("/preUpdateCustomer")
    public void preUpdateCustomer(Integer cid,HttpServletResponse response){
         Customer customer=customerService.searchCustomerById(cid);
         if(customer!=null){
             JSONObject json=JSONObject.fromObject(customer);
             try {
                 response.setCharacterEncoding("utf-8");
                 response.getWriter().print(json.toString());
             } catch (IOException e) {
                 e.printStackTrace();
             }
         }
    }
    /*修改客户信息*/

    /**
     *
     * @param customer
     * @param response
     */
    @RequestMapping("updateCustomer")
    @RequiresPermissions("/updateCustomer")
    public void updateCustomer(Customer customer,HttpServletResponse response){
        Integer result= customerService.updateCustomer(customer);
        if(result>0){
            try {
                response.getWriter().print("success");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /*删除客户信息*/

    /**
     *
     * @param cid
     * @param response
     */
    @RequestMapping("deleteCustomerById")
    @RequiresPermissions("/deleteCustomerById")
    public void deleteCustomerById(Integer cid,HttpServletResponse response){
        Integer result= customerService.deleteCustomerById(cid);
        if(result>0){
            try {
                response.getWriter().print("success");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /*客户转移*/

    /**
     *
     * @param customer
     * @param response
     */
    @RequestMapping("transferEmployee")
    @RequiresPermissions("/transferEmployee")
    public void transferEmployee(Customer customer , HttpServletResponse response){
        System.err.println(customer);
        Integer result= customerService.transferEmployee(customer);
        if(result>0){
            try {
                response.getWriter().print("success");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
