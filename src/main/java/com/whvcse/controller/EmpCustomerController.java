package com.whvcse.controller;

import com.whvcse.pojo.Customer;
import com.whvcse.pojo.EmpCustomer;
import com.whvcse.pojo.Employees;
import com.whvcse.service.CustomerService;
import com.whvcse.service.EmpCustomerService;
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
 * 2020/6/4/10:22
 */
@Controller
public class EmpCustomerController {
    @Resource
    private EmpCustomerService empCustomerService;
    @Resource
    private CustomerService customerService;


    @RequestMapping("searchCusEmployee")
    @RequiresPermissions("/searchCusEmployee")
    public void searchCusEmployee(EmpCustomer empCustomer, HttpServletResponse response, HttpServletRequest request){
        Integer offset = Integer.parseInt(request.getParameter("offset"));
        Integer pageSize = Integer.parseInt(request.getParameter("limit"));
        int currentPage=offset/pageSize+1;
        Page<EmpCustomer> page=new Page<>(currentPage,pageSize);
        HashMap<String,Integer> map=new HashMap<>();
        map.put("startIndex", (page.getCurrentPage()-1)*page.getPageSize());
        map.put("pageSize", page.getPageSize());
        empCustomer.setMap(map);
        List<EmpCustomer> list= empCustomerService.searchCusEmployee(empCustomer);
        int totalRecord= empCustomerService.selectTotalRecord(empCustomer.getEmpid());
        BootstrapPage<EmpCustomer> bPage=new BootstrapPage<>();
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

    @RequestMapping("addCustomerAndEmp")
    @RequiresPermissions("/addCustomerAndEmp")
    public void addCustomer(Customer customer, HttpServletResponse response){
        Integer result=customerService.insertCustomer(customer);
        if(result>0){
            try {
                response.getWriter().print("success");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
   /*@RequestMapping("transferEmployee")
    public void transferEmployee(Integer empid,Integer cid,Integer r_empid,HttpServletResponse response) throws IOException {
        System.err.println(empid+"sefsefse"+cid+"sefsefsef"+r_empid);
        Integer result= empCustomerService.transferEmployee(new EmpCustomer(empid,cid,new Employees(r_empid)));
        if(result>0){
            response.getWriter().print("success");
        }
    }*/
}
