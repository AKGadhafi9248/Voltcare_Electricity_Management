package com.example.myproject.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

public class PaymentHistory {

	  private Long consumerNumber;

	  private Date billDate;

	  private String billDescription;

	  private BigDecimal billAmount;

	  private Timestamp paymentDate;



	  // Getters and setters for each field



	  public Long getConsumerNumber() {

	    return consumerNumber;

	  }



	  public void setConsumerNumber(Long consumerNumber) {

	    this.consumerNumber = consumerNumber;

	  }



	  public Date getBillDate() {

	    return billDate;

	  }



	  public void setBillDate(Date billDate) {

	    this.billDate = billDate;

	  }



	  public String getBillDescription() {

	    return billDescription;

	  }



	  public void setBillDescription(String billDescription) {

	    this.billDescription = billDescription;

	  }



	  public BigDecimal getBillAmount() {

	    return billAmount;

	  }



	  public void setBillAmount(BigDecimal billAmount) {

	    this.billAmount = billAmount;

	  }



	  public Timestamp getPaymentDate() {

	    return paymentDate;

	  }



	  public void setPaymentDate(Timestamp timestamp) {

	    this.paymentDate = timestamp;

	  }

	}