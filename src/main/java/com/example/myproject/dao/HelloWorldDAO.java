package com.example.myproject.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.myproject.model.Consumer;



public interface HelloWorldDAO extends JpaRepository<Consumer, Long> {

}