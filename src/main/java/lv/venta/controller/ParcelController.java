package lv.venta.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
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
	@GetMapping("/show/customer/{id}")
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
	@GetMapping("/show/driver/{id}")
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
	@GetMapping("/show/price/{threshold}")
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
	@GetMapping("/show/city/{cityparam}")
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
	
	//Get un Post- /parcel/add/{customercode}/{driverid} NESTRĀDĀ
	@GetMapping("/add/{customercode}/{driverid}")
	public String getParcelInsert(@PathVariable("customercode") String customer_code,
			@PathVariable("driverid") int id ,Model model) throws Exception {

		try {
            model.addAttribute("parcel", new Parcel());
            model.addAttribute("customercode", customer_code); //ja kļūda tad šo pazaudē, tāpēc padod tālāk
            model.addAttribute("driverid", id); //ja kļūda tad šo pazaudē, tāpēc padod tālāk
            return "parcel-add-page";
        } catch (Exception e) {
            model.addAttribute("mydata", e.getMessage());
            return "error-page"; 
        }

	}
	
	@PostMapping("/add/{customercode}/{driverid}")
	public String postParcelInsert(@PathVariable("customercode") String customer_code,
            @PathVariable("driverid") int id,@Valid Parcel parcel, BindingResult result, Model model) {// ienāk aizpildītais parcel
		// vai ir kādi validācijas pāŗkāpumi
		System.out.println(parcel);
		System.out.println(customer_code);
		System.out.println(id);
		if (result.hasErrors()) {
			return "parcel-add-page"; // turpinām palikt product-insert-page.html lapā
		} else {
			try {
				System.out.println("Pirms");
				parcelService.insertNewParcelByCustomerCodeAndDriverId(customer_code, id, parcel);
				System.out.println("Pēc");
				return "redirect:/parcel/show/customer/"+parcel.getAbstractCustomer().getIdac();
			} catch (Exception e) {
				model.addAttribute("mydata", e.getMessage());
	            return "error-page";
			}
		}

	}
	
	
	//Get- /parcel/change/{parcelid}/{driverid} WORKS
	@GetMapping("/change/{parcelid}/{driverid}")
	public String getChangeParcelDriver(@PathVariable("parcelid") int id1,
			@PathVariable("driverid") int id2, Model model) throws Exception {
		parcelService.changeParcelDriverByParcelIdAndDriverId(id1,id2);
		return getCustomerById(id1, model);			
		
	}
	
	//Get- /parcel/calculate/income/{customerid} WORKS
	@GetMapping("/calculate/income/{customerid}")
	public String getIncomeByCustomerId(@PathVariable("customerid") int id, Model model) {
		try {
			float result = parcelService.calculateIncomeOfParcelsByCustomerId(id);
			model.addAttribute("mydata", "Total income: " + result + " euro");
			return "parcel-calculate-page";			
		} catch (Exception e) {
			model.addAttribute("mydata", e.getMessage());
			return "error-page";
		}
	}
	
	//Get- /parcel/calculate/count/today WORKS
	@GetMapping("/calculate/count/today") 
	public String getHowManyParcelsDeliverToday(Model model) {
		try
		{
			int result = parcelService.calculateHowManyParcelsNeedToDeliverToday();
			model.addAttribute("mydata", "Total " + result + " parcels need to be delivered today!");
			return "parcel-calculate-page";
		}
		catch (Exception e) {
			model.addAttribute("mydata", e.getMessage());
			return "error-page";
		}
	}
	
}
