package com.example.myproject.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.myproject.model.Feedback;
import com.example.myproject.service.DatabaseUtil;


import jakarta.servlet.http.HttpSession;

@Controller

public class AdminFeedbackController {



  @GetMapping("/admin/feedbacks")

  public String viewAllFeedbacks(@RequestParam(required = false) String search, Model model,HttpSession session) {

    List<Feedback> feedbackList;



    if (search != null && !search.isEmpty()) {

      feedbackList = DatabaseUtil.searchFeedbacks(search);

    } else {

      feedbackList = DatabaseUtil.getAllFeedbacks();

    }



    model.addAttribute("feedbackList", feedbackList);

    model.addAttribute("search", search);



    return "adminFeedback"; // JSP or Thymeleaf page for admin feedback view

  }

}


