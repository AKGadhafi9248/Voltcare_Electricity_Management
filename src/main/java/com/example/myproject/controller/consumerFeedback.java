package com.example.myproject.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.myproject.model.Feedback;
import com.example.myproject.service.DatabaseUtil;

import jakarta.servlet.http.HttpSession;

@Controller

public class consumerFeedback {



  @GetMapping("/consumer/feedbacks")

  public String viewActiveFeedbacks(HttpSession session, Model model) {

    String userId= (String)session.getAttribute("userId");
	 System.out.println(userId);
	 model.addAttribute("con_num",DatabaseUtil.findConsumerNumberById(userId) );
	 System.out.println(model.getAttribute("con_num"));
	    Long consumerNumber = (Long) model.getAttribute("con_num");
		 System.out.println("in consumer feedback:"+consumerNumber);


    if (consumerNumber == null) {

      model.addAttribute("message", "Consumer not logged in.");

      return "error"; 

    }


    List<Feedback> activeFeedbacks = DatabaseUtil.getActiveFeedbacksForConsumer(consumerNumber);
System.out.println(activeFeedbacks);
    model.addAttribute("activeFeedbacks", activeFeedbacks);


    return "feedback";

  }



  @PostMapping("/consumer/submitFeedback")

  public String submitFeedback(

      @RequestParam("feedbackId") int feedbackId,

      @RequestParam("commandDescription") String commandDescription,

      @RequestParam("rating") int rating,

      RedirectAttributes redirectAttributes) {



    // Insert feedback logic (assuming it updates the table properly)

    DatabaseUtil.submitFeedback(feedbackId, commandDescription, rating);



    // Update feedback status to 'inactive'

    DatabaseUtil.updateFeedbackStatusByComplaintId(feedbackId, "inactive");



    // Add a success message

    redirectAttributes.addFlashAttribute("message", "Feedback submitted successfully.");



    return "redirect:/consumer/feedbacks";

  }

}