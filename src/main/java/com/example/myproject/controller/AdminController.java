package com.example.myproject.controller;



import com.example.myproject.model.Consumer;

import com.example.myproject.model.Bill;
import com.example.myproject.model.Compliant;
import com.example.myproject.service.DatabaseUtil;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Controller
public class AdminController {

  // Method to display the Add Consumer Bill page
	

  @GetMapping("/addConsumerBill")

  public String showAddConsumerBillPage(HttpSession session, Model model) {



	  List<Consumer> consumers = DatabaseUtil.getAllConsumers();



	  // Store the list of consumers in the model

	  model.addAttribute("consumers", consumers);
      System.out.println(consumers);


	  return "addConsumerBill"; // JSP name without extension

	}



  // Method to handle the form submission for adding a consumer bill

  @PostMapping("/addConsumerBill")

  public String addConsumerBill(@RequestParam("consumerNumber") Long consumerNumber,

                 @RequestParam("billDescription") String billDescription,

                 @RequestParam("billAmount") int billAmount,
                 @RequestParam("billDate") LocalDate localDate,

                 RedirectAttributes redirectAttributes) {

	
	System.out.println(consumerNumber);

    Bill bill = new Bill();

    bill.setConsumerNumber(consumerNumber);

    bill.setBillDescription(billDescription);

    bill.setBillAmount(billAmount);
    bill.setBillDate(localDate);
    



    // Insert the new bill into the database

    DatabaseUtil.insertBill(bill);


    
    // Optionally, you can add a message for confirmation or redirect to another page

    redirectAttributes.addFlashAttribute("message", "Bill added successfully for consumer " + consumerNumber);



    return "redirect:/addConsumerBill";

  }
  @GetMapping("/active-complaints")

  public String showActiveComplaints(HttpSession session, Model model) {

    List<Compliant> complaints = DatabaseUtil.getActiveComplaints();

    model.addAttribute("complaints", complaints);

    return "activeComplaints"; // JSP name

  }



  // Update complaint status to Pending

  @PostMapping("/set-pending/{id}")

  public String setComplaintToPending(@PathVariable int id, RedirectAttributes redirectAttributes) {

    DatabaseUtil.updateComplaintStatus(id, "Pending");

    redirectAttributes.addFlashAttribute("message", "Complaint set to Pending.");

    return "redirect:/active-complaints";

  }



  // Update complaint status to Inactive

  
  @PostMapping("/set-inactive/{id}")

  public String setComplaintToInactive(@PathVariable int id, HttpSession session, RedirectAttributes redirectAttributes) {

    // Retrieve complaint details

    Compliant complaint = DatabaseUtil.getComplaintById(id);

    if (complaint != null) {

      // Set the complaint status to "Resolved"

      DatabaseUtil.updateComplaintStatus(id, "Resolved");



      // Check if feedback already exists for the complaint

      if (!DatabaseUtil.feedbackExistsForComplaint(id)) {

        // Insert new feedback record

        DatabaseUtil.insertFeedback(id, "Provide your feedback", 1); // Initial rating is 0

      }



      // Update feedback status to "active"

      DatabaseUtil.updateFeedbackStatusByComplaintId(id, "active");



      redirectAttributes.addFlashAttribute("message", "Complaint set to Resolved and feedback status set to Active.");

    } else {

      redirectAttributes.addFlashAttribute("error", "Complaint not found.");

    }

    return "redirect:/active-complaints";

  }








 

}
/*
 * DatabaseUtil.updateFeedbackStatus(id, "active");
 * 
 * 
 * 
 * // Store complaint details in the session
 * 
 * session.setAttribute("complaintId", complaint.getComplaintId());
 * 
 * session.setAttribute("complaintType", complaint.getComplaintType());
 * 
 * session.setAttribute("problemDescription",
 * complaint.getProblemDescription());
 */
