package com.example.myproject.interceptor;
import jakarta.servlet.http.HttpServletRequest;

import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

import org.springframework.web.servlet.HandlerInterceptor;



@Component

public class AuthInterceptor implements HandlerInterceptor {



  @Override

  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

    HttpSession session = request.getSession(false); // Get the session, do not create a new one


    if (session == null) {

      response.sendRedirect("/"); 

      return false;

    }

    String sessionToken = (String) session.getAttribute("sessionToken");

    String adminSessionToken = (String) session.getAttribute("adminSessionToken");

    String requestToken = request.getParameter("token");


    if (sessionToken != null && (requestToken == null || !sessionToken.equals(requestToken))) {

      session.invalidate(); // Invalidate session

      response.sendRedirect("/"); // Redirect to login

      return false;

    }



    // If token is missing or invalid for admins

    if (adminSessionToken != null && (requestToken == null || !adminSessionToken.equals(requestToken))) {

      session.invalidate(); // Invalidate session

      response.sendRedirect("/"); // Redirect to login

      return false;

    }



    // If no valid tokens are found in the session, force logout

    if (sessionToken == null && adminSessionToken == null) {

      response.sendRedirect("/");

      return false;

    }



    return true; // Allow the request to proceed if token validation passes

  }

}