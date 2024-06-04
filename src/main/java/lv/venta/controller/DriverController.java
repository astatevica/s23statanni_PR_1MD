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
	
	//Get- /driver/show/all WORKS
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
	
	//Get- /driver/show/all/{id} WORKS
	@GetMapping("/show/all/{id}")//localhost:8080/driver/show/all/{id}
	public String getDriverById(@PathVariable("id") int id, Model model) {
		try {
			Driver selectedDriver = driverService.retrieveById(id);
			model.addAttribute("mydata",selectedDriver);//padodam izfiltreto Driver uz driver-all-page.html lapu
			model.addAttribute("msg", "Driver filtered by id");
			return "driver-all-page";//paradam pasu driver-all-page.html lapu interneta parluka
		} catch (Exception e) {
			model.addAttribute("mydata",e.getMessage());//padod kludas zinu uz error-page.html lapu
			return "error-page";//paradam pasu error-page.html lapu interneta parluka
		}
	}
	
	//Get- /driver/remove/{id} WORKS
	@GetMapping("/remove/{id}") //localhost:8080/driver/remove/{id}
	public String getDriverDeleteById(@PathVariable("id") int id, Model model) {
		
		try {
			driverService.deleteById(id);
			ArrayList<Driver> allDrivers = driverService.retrieveAll(); 
			model.addAttribute("mydata", allDrivers);//padod izfiltrētos driver uz driver-all-page.html
			model.addAttribute("msg", "All drivers with out deleted driver no: " + id);
			return "driver-all-page";
		} catch (Exception e) {
			model.addAttribute("mydata", e.getMessage());
			return "error-page";
		}
		
	}
	
	//Get un Post- /driver/add NESTRĀDĀ->nepārslēdzas
	@GetMapping("/add")
	public String getDriverInsert(Model model) {//ienāk aizpildītais driver
		model.addAttribute("driver", new Driver());
		return "driver-add-page";
		
	} 
	
	@PostMapping("/add")
	public String postDriverInsert(@Valid Driver driver, BindingResult result) throws Exception {// ienāk aizpildītais driver
		// vai ir kādi validācijas pāŗkāpumi
		if (result.hasErrors()) {
			return "driver-add-page"; // turpinām palikt driver-add-page.html lapā
		} else {
			driverService.create(driver);
			return "redirect:/driver/show/all";
		}

	}
	
	//Get un Post- /driver/update/{id} NESTRĀDĀ->nepārslēdzas
	@GetMapping("/update/{id}") //localhost:8080/driver/update/1
	public String getDriverUpdateById(@PathVariable("id") int id, Model model) {
		
		try {
			Driver driverForUpdating = driverService.retrieveById(id);
			model.addAttribute("driver",driverForUpdating);
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
				return "redirect:/driver/show/all/"+ id;//pārlecu uz /show/all/{id} galapunktu
			} catch (Exception e) {
				model.addAttribute("mydata", e.getMessage());
				return "error-page";
			}
		}
	}
	
}
