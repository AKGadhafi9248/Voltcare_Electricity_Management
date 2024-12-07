package com.example.myproject.controller;

import java.io.IOException;
import java.net.http.HttpHeaders;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.myproject.model.PaymentHistory;
import com.example.myproject.service.DatabaseUtil;

import jakarta.servlet.http.HttpSession;


@Controller
public class historyController {

	@GetMapping("/downloadReceipt")

	public ResponseEntity<Resource> downloadReceipt() {

	  String tempDir = System.getProperty("java.io.tmpdir");

	Path path = Paths.get(tempDir, "receipt.txt");



	Resource resource = new FileSystemResource(path.toFile());



	return ResponseEntity.ok()

	    .header("Content-Disposition", "attachment; filename=receipt.txt")

	    .contentType(MediaType.APPLICATION_OCTET_STREAM)

	    .body(resource);

	}
	 @GetMapping("/showhistory")
	 
		public String viewHistory(Model model,HttpSession session) {

		 String userId= (String)session.getAttribute("userId");
		 System.out.println("userid in history:"+userId);
		 model.addAttribute("con_num",DatabaseUtil.findConsumerNumberById(userId) );
		 Long number = (Long) model.getAttribute("con_num");
	     System.out.println("number in history:"+number);
			 
			 List<PaymentHistory> billHistory = DatabaseUtil.getBillHistory(number);
		  model.addAttribute("billHistory", billHistory);

		  return "history"; 

		}

}
