package lv.venta.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lv.venta.model.City;
import lv.venta.model.Parcel;
import lv.venta.service.IParcelService;

@Controller
@RequestMapping("/parcel")
public class ParcelController {
	
	@Autowired
	private IParcelService parcelService;

	//Get- /parcel/show/customer/{id} WORKS
	//TODO getIs_fragile nestrādā
	@GetMapping("show/customer/{id}")
	public String getCustomerById(@PathVariable("id") int id, Model model) {
		try {
			ArrayList<Parcel> result = parcelService.selectAllParcelsByCustomerId(id);
			model.addAttribute("mydata", result);
			model.addAttribute("msg", "Parcels filtered by customer id");
			return "parcel-all-page";			
		} catch (Exception e) {
			model.addAttribute("mydata", e.getMessage());
			return "error-page";
		}
	}
	
	//Get- /parcel/show/driver/{id} WORKS
	@GetMapping("show/driver/{id}")
	public String getDriverById(@PathVariable("id") int id, Model model) {
		try {
			ArrayList<Parcel> result = parcelService.selectAllParcelsDeliveredByDriverId(id);
			model.addAttribute("mydata", result);
			model.addAttribute("msg", "Parcels filtered by driver id");
			return "parcel-all-page";			
		} catch (Exception e) {
			model.addAttribute("mydata", e.getMessage());
			return "error-page";
		}
	}
	
	//Get- /parcel/show/price/{threshold} WORKS
	@GetMapping("show/price/{threshold}")
	public String getParcelsLessThanPrice(@PathVariable("threshold") float price, Model model) {
		try {
			ArrayList<Parcel> result = parcelService.selectAllParcelsPriceLessThan(price);
			model.addAttribute("mydata", result);
			model.addAttribute("msg", "Parcels filtered by price less than");
			return "parcel-all-page";			
		} catch (Exception e) {
			model.addAttribute("mydata", e.getMessage());
			return "error-page";
		}
	}
	
	//Get- /parcel/show/city/{cityparam} WORKS
	@GetMapping("show/city/{cityparam}")
	public String getParceDrivedToCity(@PathVariable("cityparam") City city, Model model) {
		try {
			ArrayList<Parcel> result = parcelService.selectAllParcelsDeliveredToCity(city);
			model.addAttribute("mydata", result);
			model.addAttribute("msg", "Parcels filtered by city");
			return "parcel-all-page";			
		} catch (Exception e) {
			model.addAttribute("mydata", e.getMessage());
			return "error-page";
		}
	}
	
	//Get un Post- /parcel/add/{customercode}/{driverid}
	//Get- /parcel/change/{parcelid}/{driverid}
	//Get- /parcel/calculate/income/{customerid}
	//Get- /parcel/calculate/count/today
}
