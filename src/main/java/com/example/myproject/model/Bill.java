package com.example.myproject.model;



import java.time.LocalDate;



public class Bill {



  private int billId;

  private long consumerNumber;

  private String billDescription;

  private int billAmount;

  private LocalDate billDate;



  // Getters and setters



  public int getBillId() {

    return billId;

  }



  public void setBillId(int billId) {

    this.billId = billId;

  }



  public long getConsumerNumber() {

    return consumerNumber;

  }



  public void setConsumerNumber(long consumerNumber) {

    this.consumerNumber = consumerNumber;

  }



  public String getBillDescription() {

    return billDescription;

  }



  public void setBillDescription(String billDescription) {

    this.billDescription = billDescription;

  }



  public int getBillAmount() {

    return billAmount;

  }



  public void setBillAmount(int billAmount) {

    this.billAmount = billAmount;

  }



  public LocalDate getBillDate() {

    return billDate;

  }



  public void setBillDate(LocalDate billDate) {

    this.billDate = billDate;

  }

}