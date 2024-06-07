package lv.venta.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import lv.venta.model.AbstractCustomer;
import lv.venta.model.CustomerAsCompany;
import lv.venta.model.CustomerAsPerson;
import lv.venta.service.ICustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired 
	private ICustomerService customerService;
	
	//TODO uztaisīt customer show all
	// Get un Post- /customer/create/person
	@GetMapping("/create/person")
	public String getCustomerPersonInsert(Model model) {//ienāk aizpildītais person
		model.addAttribute("customerAsPerson", new CustomerAsPerson());
		return "customer-person-create-page";
			
	} 
		
	@PostMapping("/create/person")
	public String postCustomerPersonInsert(@Valid CustomerAsPerson customerAsPerson, BindingResult result) throws Exception {// ienāk aizpildītais person
		// vai ir kādi validācijas pāŗkāpumi
		if (result.hasErrors()) {
			return "customer-person-create-page"; // turpinām palikt customer-person-create-page.html lapā
		} else {
			customerService.insertNewCustomerAsPerson(customerAsPerson);
			return "redirect:/driver/show/all";
		}

	}
	
	
	 //Get un Post- /customer/create/company
	@GetMapping("/create/company")
	public String getCustomerCompanyInsert(Model model) {//ienāk aizpildītais person
		model.addAttribute("customerAsCompany", new CustomerAsCompany());
		return "customer-company-create-page";
			
	} 
		
	@PostMapping("/create/company")
	public String postCustomerCompanyInsert(@Valid CustomerAsCompany customerAsCompany, BindingResult result) throws Exception {// ienāk aizpildītais person
		// vai ir kādi validācijas pāŗkāpumi
		if (result.hasErrors()) {
			return "customer-company-create-page"; // turpinām palikt customer-person-create-page.html lapā
		} else {
			customerService.insertNewCustomerAsCompany(customerAsCompany);
			return "redirect:/driver/show/all";
		}

	}
	
	 //Get un Post- /customer/add/address/{customerid}
	@GetMapping("/add/address/{customerid}") //localhost:8080/driver/update/1
	public String getCustomerUpdateById(@PathVariable("customerid") int id, Model model) {
		
		try {
			AbstractCustomer customerForUpdating = customerService.retrieveById(id);
			model.addAttribute("customer",customerForUpdating);
			model.addAttribute("customerid", id);
			System.out.println(customerForUpdating);
			System.out.println(id);
			return "customer-add-address-page"; // parādām driver-update-page.html, padodot atlasīto produktu
			
		} catch (Exception e) {
			model.addAttribute("mydata", e.getMessage());
			return "error-page";
		}
		
	}
	
	@PostMapping("/add/address/{customerid}")
	public String postCustomerUpdateById(@PathVariable("customerid") int id, 
			@Valid AbstractCustomer abstractCustomer, BindingResult result, Model model) {
		System.out.println(abstractCustomer);
		System.out.println(id);
		if(result.hasErrors()) {
			return "customer-add-address-page";
		}else {
			try {
				customerService.addAddressToCustomerByCustomerId(id, abstractCustomer);
				return "redirect:/driver/show/all"; //+ id;//pārlecu uz /show/all/{id} galapunktu
			} catch (Exception e) {
				model.addAttribute("mydata", e.getMessage());
				return "error-page";
			}
		}
	}
	
}
