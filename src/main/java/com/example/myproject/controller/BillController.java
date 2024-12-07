package com.example.myproject.controller;



import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;

import com.example.myproject.service.DatabaseUtil;

import jakarta.servlet.http.HttpSession;



@Controller

public class BillController {

  @GetMapping("/payBill")

  public String showBills(Model model,HttpSession session) {
	 String userId= (String)session.getAttribute("userId");
	 System.out.println(userId);

    model.addAttribute("connumber",DatabaseUtil.findConsumerNumberById(userId) );
    
    System.out.println(model.getAttribute("connumber"));
    Long number = (Long) model.getAttribute("connumber");
    model.addAttribute("billList",DatabaseUtil.findBillsByConsumerNumber(number));
    return "payBill"; 

  }

} 