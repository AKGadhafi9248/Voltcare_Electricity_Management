package com.example.myproject.model;

public class Feedback {

	  private int feedbackId;

	  private int complaintId;

	  private String consumerNumber;

	  private String commandDescription;

	  private int rating;

	  private String feedbackStatus;



	  // Getters and Setters

	  public int getFeedbackId() { return feedbackId; }

	  public void setFeedbackId(int feedbackId) { this.feedbackId = feedbackId; }



	  public int getComplaintId() { return complaintId; }

	  public void setComplaintId(int complaintId) { this.complaintId = complaintId; }



	  public String getConsumerNumber() { return consumerNumber; }

	  public void setConsumerNumber(String consumerNumber) { this.consumerNumber = consumerNumber; }



	  public String getCommandDescription() { return commandDescription; }

	  public void setCommandDescription(String commandDescription) { this.commandDescription = commandDescription; }



	  public int getRating() { return rating; }

	  public void setRating(int rating) { this.rating = rating; }



	  public String getFeedbackStatus() { return feedbackStatus; }

	  public void setFeedbackStatus(String feedbackStatus) { this.feedbackStatus = feedbackStatus; }

	}