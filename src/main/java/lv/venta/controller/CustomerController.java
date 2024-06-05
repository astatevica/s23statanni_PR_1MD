package lv.venta.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import lv.venta.model.CustomerAsPerson;
import lv.venta.service.ICustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired 
	private ICustomerService customerService;
	
	//Get un Post- /customer/create/person NESTRĀDĀ
	@GetMapping("/create/person") // localhost:8080/product/crud/insert
	public String getCustomerAsPersonInsert(Model model) {

		model.addAttribute("customerAsPerson", new CustomerAsPerson());
		return "customer-person-create-page"; // tiks parādīta product-insert-page.html lapa ar iedotu tuksu produktu

	}

	@PostMapping("/create/person")
	public String postCustomerAsPersonInsert(@Valid CustomerAsPerson customerAsPerson, BindingResult result) throws Exception {// ienāk aizpildītais produkts
		// vai ir kādi validācijas pāŗkāpumi
		if (result.hasErrors()) {
			return "customer-person-create-page"; // turpinām palikt product-insert-page.html lapā
		} else {
			customerService.insertNewCustomerAsPerson(customerAsPerson.getPerson().getPersonCode());
			return "customer-person-create-page";
		}

	}
	
	
	 //Get un Post- /customer/create/company
	
	 //Get un Post- /customer/add/address/{customerid}
	
}
