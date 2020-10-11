package com.whvcse.pojo;


import lombok.*;

import java.util.HashMap;

@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Customer {

  private Integer cid;
  private String cusName;
  private String address;
  private String contact;
  private String tel;
  private String email;
  private HashMap<String,Integer> map;
  private Employees employees;
  private Integer eid;

}
