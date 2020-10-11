package com.whvcse.pojo;

import java.util.HashMap;

public class Employees {

  private Integer empid;
  private String username;
  private String password;
  private String tel;
  private String name;
  private String email;
  private String rolename;
  private HashMap<String,Integer> map;

    public Employees() {

    }

    public Employees(Integer empid) {
        this.empid = empid;
    }

    public Employees(String username) {
        this.username = username;
    }

    public HashMap<String, Integer> getMap() {
        return map;
    }

    public void setMap(HashMap<String, Integer> map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return "Employees{" +
                "empid=" + empid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", tel='" + tel + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", rolename='" + rolename + '\'' +
                '}';
    }
    public Integer getEmpid() {
    return empid;
  }

  public void setEmpid(Integer empid) {
    this.empid = empid;
  }


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  public String getTel() {
    return tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }


  public String getRolename() {
    return rolename;
  }

  public void setRolename(String rolename) {
    this.rolename = rolename;
  }

}
