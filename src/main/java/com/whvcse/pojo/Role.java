package com.whvcse.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {

  private Integer rid;
  private String rolename;
  private HashMap<String,Integer> map;

    public Role(String rolename) {
        this.rolename = rolename;
    }
}
