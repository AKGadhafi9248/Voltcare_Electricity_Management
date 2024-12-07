package com.example.myproject.controller;



import com.example.myproject.model.Compliant;
import com.example.myproject.model.Consumer;

import com.example.myproject.service.DatabaseUtil;

import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



@Controller

public class RegisterController {

	 @GetMapping("/")

	  public String indexPage() {

	    return "index"; // JSP located at WEB-INF/views/login.jsp

	  }


  @GetMapping("/register")

  public String showRegistrationForm() {

    return "register"; // JSP located at WEB-INF/views/register.jsp

  }



  @GetMapping("/home")

  public String showHomeForm() {

    return "home"; // JSP located at WEB-INF/views/home.jsp

  }
  
  @GetMapping("/history")

  public String showHistoryPage() {

    return "history"; // JSP located at WEB-INF/views/login.jsp

  }

  @GetMapping("/logout")

  public String showLogoutPage(HttpSession session) {
	  session.invalidate();

    return "redirect:/"; // JSP located at WEB-INF/views/login.jsp

  }
  @GetMapping("/admin")

  public String showAdminPage() {

    return "admin"; // JSP located at WEB-INF/views/login.jsp

  }
//  @GetMapping("/addConsumerBill")
//
//  public String showAddConsumerBillPage(Model model) {
//
//    List<Consumer> consumers = DatabaseUtil.getAllConsumers(); // Fetch consumers from the database
//
//    model.addAttribute("consumers", consumers);
//
//    return "addConsumerBill"; // return the JSP page to display
//
//  }

  @GetMapping("/login")

  public String showLoginPage() {

    return "login"; // JSP located at WEB-INF/views/login.jsp

  }



  @PostMapping("/login")

  public String login(

      @RequestParam("userId") String userId,

      @RequestParam("password") String password,

      HttpSession session,

      Model model,  RedirectAttributes redirectAttributes) {
	  redirectAttributes.addFlashAttribute("userid", userId);
    if (DatabaseUtil.isUserIdExist(userId)) {

      Consumer consumer = DatabaseUtil.findUserById(userId);

      if (consumer != null && consumer.getPassword().equals(password)) {

        session.setAttribute("userName", consumer.getName());
        session.setAttribute("userId", userId);
        

        if ("customer".equalsIgnoreCase(consumer.getCategory())) {

        	  String sessionToken = UUID.randomUUID().toString();

        	  session.setAttribute("sessionToken", sessionToken);

        	  return "redirect:/home?token=" + sessionToken;

        	} else  {

        	  String adminSessionToken = UUID.randomUUID().toString();

        	  session.setAttribute("adminSessionToken", adminSessionToken);

        	  return "redirect:/admin?token=" + adminSessionToken;

        	} 

      } else {

        model.addAttribute("erMessage", "Invalid credentials. Please try again.");

        return "login";

      }

    } else {

      model.addAttribute("errorUser", "User ID does not exist. Please register.");

      return "login";

    }

  }


  @PostMapping("/registerControl")

  public String registerConsumer(

      @RequestParam String consumerNumber,

      @RequestParam String billNumber,

      @RequestParam String title,

      @RequestParam String name,

      @RequestParam String email,

      @RequestParam String mobile,

      @RequestParam String category,

      @RequestParam String userId,

      @RequestParam String password,

      @RequestParam String conpassword,

      Model model) {



    boolean hasErrors = false;



    if (consumerNumber == null || consumerNumber.isEmpty()) {

      model.addAttribute("errorConsumerNumber", "Consumer Number is required.");

      hasErrors = true;

    } else if (DatabaseUtil.isConsumerNumberExist(consumerNumber)) {

      model.addAttribute("errorConsumerNumber", "Consumer Number already exists.");

      hasErrors = true;

    }



    // Bill number validation: should not be empty or "00000000"

    if (billNumber == null || billNumber.isEmpty() || billNumber.equals("00000")) {

      model.addAttribute("errorBillNumber", "Invalid Bill Number. It cannot be '00000'.");

      hasErrors = true;

    }



    // Name validation

    if (name == null || name.isEmpty() || name.contains("1") || name.contains("2") || name.contains("3") || name.contains("4") || name.contains("5") || name.contains("6") || name.contains("7") || name.contains("8") || name.contains("9") || name.contains("0")) {

      model.addAttribute("errorName", "Name is required and Name should not be contain numerical.");

      hasErrors = true;

    }



    // Email validation

    if (email == null || email.isEmpty() || !email.contains("@")) {

      model.addAttribute("errorEmail", "Valid Email is required.");

      hasErrors = true;

    }



    // Mobile number validation: should not be empty or "00000000"

    if (mobile == null || mobile.isEmpty() || mobile.equals("0000000000") || mobile.matches("^[6-9]$")){

      model.addAttribute("errorMobile", "Invalid Mobile Number. It should start with a digit between 6-9.");

      hasErrors = true;

    }



    // Password validation

    if (password == null || !password.equals(conpassword)) {

      model.addAttribute("passwordMismatch", "Passwords do not match.");

      hasErrors = true;

    }



    if (hasErrors) {

      return "register"; // Returns to the registration page if there are errors

    }



    // Create the Consumer object and populate it with form data

    Consumer consumer = new Consumer();

    consumer.setConsumerNumber(Long.parseLong(consumerNumber));

    consumer.setBillNumber(Integer.parseInt(billNumber));

    consumer.setTitle(title);

    consumer.setName(name);

    consumer.setEmail(email);

    consumer.setMobile(mobile);

    consumer.setCategory(category);

    consumer.setUserId(userId);

    consumer.setPassword(password);
Random ran= new Random();


    // Store consumer in the database

    DatabaseUtil.insertConsumer(consumer);
    model.addAttribute("customerId", "EC"+ ran.nextInt(10000));
    model.addAttribute("consumerEmail", email);

    model.addAttribute("consumerName", name);

    return "success"; // Redirect to success page upon successful registration

  }

}