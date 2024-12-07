//package com.example.myproject.controller;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import com.example.myproject.service.DatabaseUtil;
//
//@Controller
//public class sumbitFeedback {
//
//	@GetMapping("/submitFeedback")
//
//	  public String submitFeedback() {
//
//	    return "feedback";
//
//	  }
//	 @PostMapping("/submitFeedback")
//
//	  public String submitFeedback(@RequestParam int complaintId,
//
//	                 @RequestParam String commandDescription,
//
//	                 @RequestParam int rating,
//
//	                 RedirectAttributes redirectAttributes) {
//
//	    DatabaseUtil.submitFeedback(complaintId, commandDescription, rating);
//
//	    DatabaseUtil.updateFeedbackStatus(complaintId, "inactive");
//
//	    redirectAttributes.addFlashAttribute("message", "Feedback submitted successfully.");
//
//	    return "redirect:/home"; // Redirect to home or complaint status page
//
//	  }
//}
