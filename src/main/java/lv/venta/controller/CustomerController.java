package lv.venta.controller;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
			return "redirect:/customer/show/all";
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
			return "redirect:/customer/show/all";
		}

	}
	
	 //Get un Post- /customer/add/address/{customerid}
	@GetMapping("/add/address/{customerid}") //localhost:8080/driver/update/1
	public String getCustomerUpdateById(@PathVariable("customerid") int id, Model model) {
		
		try {
			AbstractCustomer customerForUpdating = customerService.retrieveById(id);
			model.addAttribute("customer",customerForUpdating);
			model.addAttribute("customerid", id);
			return "customer-add-address-page"; // parādām driver-update-page.html, padodot atlasīto produktu
			
		} catch (Exception e) {
			model.addAttribute("mydata", e.getMessage());
			return "error-page";
		}
		
	}
	
	@PostMapping("/add/address/{customerid}")
	public String postCustomerUpdateById(@PathVariable("customerid") int id, 
			@Valid @ModelAttribute("customer") AbstractCustomer abstractCustomer, BindingResult result, Model model) {
		System.out.println(result);
		if(result.hasErrors()) {
			return "customer-add-address-page";
		}else {
			try {
				System.out.println("Pirms");
				customerService.addAddressToCustomerByCustomerId(id, abstractCustomer);
				System.out.println("Pēc");
				return "redirect:/customer/show/all";
			} catch (Exception e) {
				model.addAttribute("mydata", e.getMessage());
				return "error-page";
			}
		}
	}
	
	//Get- /customer/show/all WORKS
	@GetMapping("/show/all")//localhost:8080/customer/show/all
	public String getAllCustomers(Model model) {
		try {
			ArrayList<AbstractCustomer> allCustomers = customerService.retrieveAll(); 
			model.addAttribute("mydata", allCustomers);//padod izfiltrētos driver uz driver-all-page.html
			model.addAttribute("msg", "All customers");
			return "customer-all-page";//paradam pasu driver-all-page.html lapu interneta parluka
		}catch (Exception e) {
			model.addAttribute("mydata",e.getMessage());//padod kludas zinu uz error-page.html lapu
			return "error-page";//paradam pasu error-page.html lapu interneta parluka
		}
	}
	
}
