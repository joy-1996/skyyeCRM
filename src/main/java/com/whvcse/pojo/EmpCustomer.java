package com.whvcse.pojo;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
@Data



public class EmpCustomer {
    private Integer empid;
    private Integer cid;
    private Employees employees;
    private Customer customer;
    private HashMap<String,Integer> map;

    public EmpCustomer(Integer empid, Integer cid, Employees employees) {
        this.empid = empid;
        this.cid = cid;
        this.employees = employees;
    }
    public EmpCustomer() {
    }
}
