package com.example.myproject.controller;



import java.io.BufferedWriter;

import java.io.FileWriter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import com.example.myproject.service.DatabaseUtil;



@Controller

public class paymentController {
	@Autowired
	  private  JdbcTemplate jdbcTemplate;
	
	@GetMapping("/makePayment")
	public String cardDetail() {
		return "card";
	}


	@PostMapping("/makePayment")

	public String makePayment(HttpServletRequest request, Model model,HttpSession session) {

	  String cardName = request.getParameter("cardName");

	  String cardNumber = request.getParameter("cardNumber");

	  String cvv = request.getParameter("cvv");

	  String expiryDate = request.getParameter("expiryDate");

	  String totalAmount = request.getParameter("totalAmount");
	  
	  List<Integer> billIds = (List<Integer>) session.getAttribute("billIds");

	  Integer totalAmount2 = (Integer) session.getAttribute("totalfinal");



	  System.out.println("Amount: " + totalAmount2);

	  System.out.println("Bill IDs: " + billIds);

	  if (billIds == null || billIds.size() == 0) {

	    model.addAttribute("error", "No bills selected for payment.");

	    return "paymentDetails";

	  }

	  List<Integer> billIds2 = new ArrayList<>();

	  for (Integer billId : billIds) {

	    billIds2.add(billId);

	  }



	  if (cardName == null || cardName.isEmpty() ) {

	    model.addAttribute("errorName", "Invalid!, Enter Correct Name.");

	    return "card";

	  }
	  if(cardNumber.matches("0+")) {
		  model.addAttribute("errorNo", "Invalid!,Enter Correct Number.");

		    return "card";
	  }
	  
	  if (expiryDate.isEmpty() || !expiryDate.matches("\\d{2}/\\d{2}")) {

		  model.addAttribute("errorDate", "Invalid format! Use MM/YY.");

		  return "card";

		}



		int month = Integer.parseInt(expiryDate.substring(0, 2));

		int year = Integer.parseInt(expiryDate.substring(3, 5));



		// Check if month is valid (1-12) and year is not less than 24

		if (month < 1 || month > 12 || year < 24) {

		  model.addAttribute("errorDate", "Invalid! Enter a correct expiry date.");

		  return "card";

		}
	  if( cvv.equalsIgnoreCase("000")) {
		  model.addAttribute("errorCvv", "Invalid!,Enter Correct Cvv.");

		    return "card";
	  }



	  // Mask the card number except the last 4 digits

	  String maskedCard = "**** **** **** " + cardNumber.substring(cardNumber.length() - 4);

	  String tempDir = System.getProperty("java.io.tmpdir");

	  String receiptFileName = "receipt.txt";

	  Path receiptPath = Paths.get(tempDir, receiptFileName);

	  // Generate receipt

	  try (BufferedWriter writer = Files.newBufferedWriter(receiptPath)) {
		  
		  writer.write("Card Holder Name: " + cardName + "\n");

		    writer.write("Card: " + maskedCard + "\n");

		    writer.write("Amount: $ " + totalAmount2 + "\n");

		    writer.write("Date: " + new Date().toString() + "\n");

		  } catch (IOException e) {

		    e.printStackTrace();

		    model.addAttribute("error", "Failed to generate receipt.");

		    return "card";

		  }
model.addAttribute("date",new Date().toString());
model.addAttribute("totalAmount",totalAmount);

System.out.println(billIds2);
	  // Call DatabaseUtil to handle history insertion and deletion

	  DatabaseUtil.updateBillHistory(billIds2);

	  model.addAttribute("receiptFilePath", "/downloadReceipt");

	  return "download"; // JSP page to download the receipt

	}



	@PostMapping("/processPayment")

	public String processPayment(@RequestParam("billIds") List<Integer> billIds, Model model, HttpServletRequest request,HttpSession session) {

	  String inClause = String.join(",", billIds.stream().map(id -> "?").toList());

	  String sql = "SELECT SUM(bill_amount) FROM bill WHERE bill_id IN (" + inClause + ")";



	  Integer totalAmount = jdbcTemplate.queryForObject(sql, billIds.toArray(), Integer.class);



	  // Set the totalAmount in the model to pass it to the JSP page
	  model.addAttribute("billIds",billIds);
	  session.setAttribute("billIds", billIds);
	  model.addAttribute("totalAmount", totalAmount);
	  session.setAttribute("totalfinal", totalAmount);



	  System.out.println("Total Amount: " + totalAmount);

	  System.out.println("Bill IDs in process: " + billIds);



	  return "paymentDetails"; // The name of your JSP page

	}
	



}