package com.whvcse.pojo;
import java.text.SimpleDateFormat;
import java.util.HashMap;


public class Customervisit {
  private Integer id;
  private Integer cid;
  private Integer empid;
  private String content;
  private String date;
  private Customer customer;
  private Employees employees;
  private HashMap<String,Integer> map;

    public HashMap<String, Integer> getMap() {
        return map;
    }

    public void setMap(HashMap<String, Integer> map) {
        this.map = map;
    }

    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Employees getEmployees() {
        return employees;
    }

    public void setEmployees(Employees employees) {
        this.employees = employees;
    }
  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }
  public Integer getCid() {
    return cid;
  }

  public void setCid(Integer cid) {
    this.cid = cid;
  }


  public Integer getEmpid() {
    return empid;
  }

  public void setEmpid(Integer empid) {
    this.empid = empid;
  }


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getDate() {
    return date;
  }

  public void setDate(java.sql.Timestamp date) {
      SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      this.date=simpleDateFormat.format(date);
  }

    @Override
    public String toString() {
        return "Customervisit{" +
                "id=" + id +
                ", cid=" + cid +
                ", empid=" + empid +
                ", content='" + content + '\'' +
                ", date='" + date + '\'' +
                ", customer=" + customer +
                ", employees=" + employees +
                ", map=" + map +
                '}';
    }
}
