package com.whvcse.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RoleEmp {

    private Integer r_id;
    private Integer emp_id;
    private HashMap<String,Integer> map;
    private Employees employees;
    private Role role;
    private RolePerm rolePerm;
    private Permission permission;

    public RoleEmp(Integer r_id, Integer emp_id) {
        this.r_id = r_id;
        this.emp_id = emp_id;
    }
}
