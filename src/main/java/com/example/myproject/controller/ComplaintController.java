package com.example.myproject.controller;

import com.example.myproject.model.Compliant;
import com.example.myproject.service.DatabaseUtil;

import jakarta.servlet.http.HttpSession;

import java.util.*;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller

public class ComplaintController {

	@GetMapping("/complaintregister")

	  public String showComplaintPage(Model model,HttpSession session) {
		 String userId= (String)session.getAttribute("userId");
		 System.out.println(userId);
		 model.addAttribute("con_num",DatabaseUtil.findConsumerNumberById(userId) );
		 System.out.println(model.getAttribute("con_num"));
		
		  model.addAttribute("complaint", new Compliant());
	    return "complaintreg"; 

	  }
	@GetMapping("/viewAllComplaints")

	public String viewAllComplaints(Model model,HttpSession session) {
		 String userId= (String)session.getAttribute("userId");
		 System.out.println(userId);
		 model.addAttribute("con_num",DatabaseUtil.findConsumerNumberById(userId) );
		 System.out.println(model.getAttribute("con_num"));
		    Long number = (Long) model.getAttribute("con_num");
			 System.out.println(number);


	  model.addAttribute("complaints",DatabaseUtil.getAllComplaints(number)); // Add to model

	  return "statusPage"; // Return the JSP name without extension


	}	
	 @GetMapping("/viewComplaints")

	  public String viewAllComplaints(Model model) {

	    List<Compliant> complaints = DatabaseUtil.getComplaints();

	    model.addAttribute("complaints", complaints);

	    return "allStatus";

	  }



	  @PostMapping("/updateComplaintStatus")

	  public String updateComplaintStatus(@RequestParam("complaintId") int complaintId,

	                    @RequestParam("status") String status,

	                    RedirectAttributes redirectAttributes) {

	    DatabaseUtil.updateComplaintStatus(complaintId, status);

	    redirectAttributes.addFlashAttribute("message", "Complaint status updated to " + status);

	    return "redirect:/viewAllComplaints";

	  }

  @PostMapping("/complaintregister")

  public String submitComplaint(@ModelAttribute Compliant complaint, Model model,HttpSession session) {
System.out.println(complaint.getMobile());
if (complaint.getMobile() == null || complaint.getMobile().isEmpty() || complaint.getMobile().equals("0000000000") ) {



model.addAttribute("errorMobile2", "Invalid Mobile Number. It should start with a digit between 6-9.");

return "complaintreg";

}
if(complaint.getContactPerson()== null || complaint.getContactPerson().isEmpty() || complaint.getContactPerson().contains("1") || complaint.getContactPerson().contains("2") || complaint.getContactPerson().contains("3") || complaint.getContactPerson().contains("4") || complaint.getContactPerson().contains("5") || complaint.getContactPerson().contains("6") || complaint.getContactPerson().contains("7") || complaint.getContactPerson().contains("8") || complaint.getContactPerson().contains("9") || complaint.getContactPerson().contains("0")) {
	model.addAttribute("errorName2", "Name is required and Name should not be contain numerical.");

	return "complaintreg";
}
 System.out.println("i am in there");

    complaint.setComplaintDate(java.time.LocalDate.now());
    session.setAttribute("comId", complaint.getComplaintId());


    // Save the complaint to the database

    DatabaseUtil.insertComplaint(complaint);



    // Add success message to the model

    model.addAttribute("message", "Complaint registered successfully!");



    // Redirect to success page

    return "successPage";

  }

}





