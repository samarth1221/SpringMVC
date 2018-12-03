package com.study.springdemo.mvc;

import javax.validation.Valid;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	//	add an init binder .. to convert trim input String
	
	//	remove leading and trailing whitespace
	
	//resolve issue for our validatino
	
	@InitBinder
	public void initBider(WebDataBinder dataBinde) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinde.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	@RequestMapping("/showForm")
	public String showForm(Model theMode) {
		theMode.addAttribute("customer", new Customer());
		
		return "customer-form";
	}
	
	@RequestMapping("/processFrom")
	public String processForm(
			@Valid @ModelAttribute("customer") Customer theCustomer,
			BindingResult thebindingResult	) {
		
		System.out.println("Last Name : |" + theCustomer.getLastName() +"|");
		
		System.out.println("Binding result : "+thebindingResult);
		
		System.out.println("\n");
		
		
		if(thebindingResult.hasErrors()) {	
			return "customer-form";
		}else {
			return "customer-confirmation";
		}
		
	}
}
  