package com.whvcse.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Permission {
  private Integer pid;
  private String permission_name;
  private Integer wid;
  private String  permissionurl;
  private HashMap<String,Integer> map;
    public Permission(String permission_name) {
        this.permission_name = permission_name;
    }
}
