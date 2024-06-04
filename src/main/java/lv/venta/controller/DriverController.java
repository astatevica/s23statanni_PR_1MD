package lv.venta.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import lv.venta.model.Driver;
import lv.venta.service.IDriverCRUDService;

@Controller
@RequestMapping("/driver")
public class DriverController {

	@Autowired
	private IDriverCRUDService driverService;
	
	//Get- /driver/show/all PIRMAIS-GATAVS
	@GetMapping("/show/all")//localhost:8080/driver/show/all
	public String getAllDriver(Model model) {
		try {
			ArrayList<Driver> allDrivers = driverService.retrieveAll(); 
			model.addAttribute("mydata", allDrivers);//padod izfiltrētos driver uz driver-all-page.html
			model.addAttribute("msg", "All drivers");
			return "driver-all-page";//paradam pasu driver-all-page.html lapu interneta parluka
		}catch (Exception e) {
			model.addAttribute("mydata",e.getMessage());//padod kludas zinu uz error-page.html lapu
			return "error-page";//paradam pasu error-page.html lapu interneta parluka
		}
	}
	
	//Get- /driver/show/all/{id} PIRMAIS-GATAVS
	@GetMapping("/show/all/{id}")//localhost:8080/driver/show/all/{id}
	public String getDriverById(@PathVariable("id") int id, Model model) {
		try {
			Driver selectedDriver = driverService.retrieveById(id);
			model.addAttribute("mydata",selectedDriver);//padodam izfiltreto Driver uz driver-all-page.html lapu
			model.addAttribute("msg", "Driver filtered by id");
			return "driver-one-page";//paradam pasu driver-one-page.html lapu interneta parluka
		} catch (Exception e) {
			model.addAttribute("mydata",e.getMessage());//padod kludas zinu uz error-page.html lapu
			return "error-page";//paradam pasu error-page.html lapu interneta parluka
		}
	}
	
	//Get- /driver/remove/{id} PIRMAIS-GATAVS
	@GetMapping("/remove/{id}") //localhost:8080/driver/remove/{id}
	public String getDriverDeleteById(@PathVariable("id") int id, Model model) {
		
		try {
			driverService.deleteById(id);
			ArrayList<Driver> allDrivers = driverService.retrieveAll();
			model.addAttribute("mydata",allDrivers);
			return "driver-all-page";
		} catch (Exception e) {
			model.addAttribute("mydata", e.getMessage());
			return "error-page";
		}
		
	}
	
	//Get un Post- /driver/add PIRMAIS-GATAVS
	@PostMapping("/add")
	public String getDriverInsert(Driver driver) {//ienāk aizpildītais driver
		System.out.println(driver);
		//TODO varbūt šis nav korekti
		return "redirect:/show/all";
	} 
	
	public String postDriverInsert(@Valid Driver driver, BindingResult result) {//ienāk aizpildītais produkts
		//vai ir kādi validācijas pāŗkāpumi
		if(result.hasErrors())
		{
			return "product-add-page"; //turpinām palikt driver-add-page.html lapā
		}
		else
		{
			try {
				driverService.create(driver);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//TODO varbūt šis nav korekti
			return "redirect:/show/all";
		}

	}
	
	//Get un Post- /driver/update/{id} PIRMAIS-GATAVS
	@GetMapping("/update/{id}") //localhost:8080/driver/update/1
	public String getDriverUpdateById(@PathVariable("id") int id, Model model) {
		
		try {
			Driver driverForUpdating = driverService.retrieveById(id);
			model.addAttribute("product",driverForUpdating);
			model.addAttribute("id", id);
			return "driver-update-page"; // parādām driver-update-page.html, padodot atlasīto produktu
			
		} catch (Exception e) {
			model.addAttribute("mydata", e.getMessage());
			return "error-page";
		}
		
	}
	
	@PostMapping("/update/{id}")
	public String postDriverUpdateById(@PathVariable("id") int id, 
			@Valid Driver driver, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "driver-update-page";
		}else {
			try {
				driverService.update(id, driver);
				return "redirect:/show/all/"+ id;//pārlecu uz /show/all/{id} galapunktu
			} catch (Exception e) {
				model.addAttribute("mydata", e.getMessage());
				return "error-page";
			}
		}
	}
	
}
