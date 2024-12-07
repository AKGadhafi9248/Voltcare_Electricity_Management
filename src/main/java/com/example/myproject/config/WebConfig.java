package com.example.myproject.config;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.myproject.interceptor.AuthInterceptor;



@Configuration

public class WebConfig implements WebMvcConfigurer {



  @Autowired

  private AuthInterceptor authInterceptor;



  @Override

  public void addInterceptors(InterceptorRegistry registry) {

    registry.addInterceptor(authInterceptor)

        .addPathPatterns("/home");

  }

}