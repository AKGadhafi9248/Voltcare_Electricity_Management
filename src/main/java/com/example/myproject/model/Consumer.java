package com.example.myproject.model;



import jakarta.persistence.Entity;

import jakarta.persistence.Id;



@Entity

public class Consumer {



  @Id

  private long consumerNumber;

  private int billNumber;

  private String title;

  private String name;

  private String email;

  private String mobile;

  private String category;

  private String userId;

  private String password;



  // Getters and Setters

  public long getConsumerNumber() {

    return consumerNumber;

  }



  public void setConsumerNumber(long consumerNumber) {

    this.consumerNumber = consumerNumber;

  }



  public int getBillNumber() {

    return billNumber;

  }



  public void setBillNumber(int billNumber) {

    this.billNumber = billNumber;

  }



  public String getTitle() {

    return title;

  }



  public void setTitle(String title) {

    this.title = title;

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



  public String getMobile() {

    return mobile;

  }



  public void setMobile(String mobile) {

    this.mobile = mobile;

  }



  public String getCategory() {

    return category;

  }



  public void setCategory(String category) {

    this.category = category;

  }



  public String getUserId() {

    return userId;

  }



  public void setUserId(String userId) {

    this.userId = userId;

  }



  public String getPassword() {

    return password;

  }



  public void setPassword(String password) {

    this.password = password;

  }
  @Override

  public String toString() {

    return "Consumer{" +

        "consumerNumber=" + consumerNumber +

        ", name='" + name + '\'' +

        ", email='" + email + '\'' +

        ", mobile='" + mobile + '\'' +

        '}';

  }

}