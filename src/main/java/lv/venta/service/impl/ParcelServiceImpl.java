package lv.venta.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import lv.venta.model.City;
import lv.venta.model.Parcel;
import lv.venta.repo.ICustomerAsCompanyRepo;
import lv.venta.repo.ICustomerAsPersonRepo;
import lv.venta.repo.IDriverRepo;
import lv.venta.repo.IParcelRepo;
import lv.venta.service.IParcelService;

public class ParcelServiceImpl implements IParcelService{
	
	@Autowired
	private IParcelRepo parcelRepo;
	
	@Autowired
	private ICustomerAsCompanyRepo customerAsCompanyRepo;
	
	@Autowired
	private ICustomerAsPersonRepo customerAsPersonRepo;
	
	@Autowired
	private IDriverRepo driverRepo;

	//PĀRBAUDĪTS
	@Override
	public ArrayList<Parcel> selectAllParcelsByCustomerId(int id) throws Exception {
		//pārbaudu id vērtību
		if(id < 0) throw new Exception("Id should be positive number!");
		
		//pārbaudu vai eksistē šādi customers
		//TODO iepsējams būs jāpārsauc id vieta
		if(!customerAsCompanyRepo.existsById(id) || !customerAsPersonRepo.existsById(id)) throw new Exception("Customer with " + id + " id doesn't exist");
		//Salieku visus vienā ArrayListā
		//TODO šeit var labāk uzlabot
		ArrayList<Parcel> result = parcelRepo.findByIdcp(id);
		result.addAll(parcelRepo.findByIdcc(id));
		
		//Pārbaudu vai nav tukšs lists
		if(result.isEmpty()) throw new Exception("There is no parcel with id: " + id + " as customer");
		
		//atgriežu rezultātu
		return result;
	}

	//PĀRBAUDĪTS
	@Override
	public ArrayList<Parcel> selectAllParcelsDeliveredByDriverId(int id) throws Exception {
		//Pārbaudu id vērtību
		if(id < 0) throw new Exception("Id should be positive number!");
		
		//Atrodu Drivers pēc id un ielieku listā 
		ArrayList<Parcel> result = parcelRepo.findByIdd(id);
		
		//Pārbaudu vai lists nav tukšs
		if(result.isEmpty()) throw new Exception("There is no parcel with id: " + id + " drivers");
		
		return result;
	}

	//PĀRBAUDĪTS
	@Override
	public ArrayList<Parcel> selectAllParcelsPriceLessThan(float price) throws Exception {
		//Pārbaudu ievadīto lielumu
		if(price < 0.0) throw new Exception("Price should be more than zero!");
		
		//Sameklēju Preci un ielieku sarakstā
		ArrayList<Parcel> result = parcelRepo.findByPriceLessThan(price);
		
		//Pārbaudu vai saraksts nav tukšs
		if(result.isEmpty()) throw new Exception("There is no parcel with price less than " + price);
		
		//Atgriežu rezultātu
		return result;
	}

	//PĀRBAUDĪTS
	@Override
	public ArrayList<Parcel> selectAllParcelsDeliveredToCity(City city) throws Exception {
		//Nepārbaudu city, jo tas ir enumerators
		
		//Meklēju Tieši CustomerAsCompany adresi un pilsētu
		ArrayList<Parcel> result = parcelRepo.findByIdccIdaCity(city);
		
		//Meklēju Tieši CustomerAsPerson adresi un pilsētu
		result.addAll(parcelRepo.findByIdcpIdaCity(city));
		
		//Pārbaudu vai lists nav tukšs
		if(result.isEmpty()) throw new Exception("There is no order to city " + city);
		
		//atgriežu rezultātu
		return result;
	}

	//PĀRBAUDĪTS
	@Override
	public void insertNewParcelByCustomerCodeAndDriverId(String customer_code, int id) throws Exception {

		//atrodu customer company pēc id
		if(customerAsCompanyRepo.findByCustomer_code(customer_code).equals(null) ||
				customerAsPersonRepo.findByCustomer_code(customer_code).equals(null)) throw new 
		Exception("Customer with following customer code: " + customer_code + " does not exists!");
		
		//atrodu driver by id
		if(driverRepo.findById(id).equals(null)) throw new 
		Exception("Driver with following id: " + id + " does not exists!");
		
		//Izveidoju noklusējuma Parcel
		Parcel newParcel = new Parcel();
		
		//Uzlieku jaunās vērtības
		newParcel.setCustomerAsCompany(customerAsCompanyRepo.findByCustomer_code(customer_code));
		newParcel.setCustomerAsPerson(customerAsPersonRepo.findByCustomer_code(customer_code));
		newParcel.setDriver(driverRepo.findByIdd(id));
		
		//Saglabāju DB un repozitorijā
		parcelRepo.save(newParcel);
		
	}

	//PĀRBAUDĪTS
	@Override
	public void changeParcelDriverByParcelIdAndDriverId(int idp, int idd) throws Exception {
		//Pārbaudu paciņas id
		if(idp < 0) throw new Exception("Parcels Id should be positive!");
		
		//Atrodu parcel
		Parcel parcelForUpdating = parcelRepo.findById(idp).get();
		//Pārbaudu vai tāda paciņa eksistē
		if(parcelForUpdating.equals(null)) throw new Exception("Parcel with Id does not exists!");
		
		//Pārbaudu drivers id
		if(idd < 0) throw new Exception("Drivers Id should be positive!");
		//Pārbaudu vai tāds Driver eksistē
		if(driverRepo.findByIdd(idd).equals(null)) throw new Exception("Driver with Id does not exists!");
		
		//Upditoju parcel uzliekot jauno Driver
		parcelForUpdating.setDriver(driverRepo.findByIdd(idd));
		
		//Saglabāju repo un DB
		parcelRepo.save(parcelForUpdating);
	}

	//PĀRBAUDĪTS
	@Override
	public float calculateIncomeOfParcelsByCustomerId(int id) throws Exception {
		//Pārbauda id vai nav negativs
		if(id < 1) throw new Exception("Id should be positive");
		
		//Parbauda vai tads kurs ar id vispar eksistee
		if(!customerAsPersonRepo.existsById(id) || !customerAsCompanyRepo.existsById(id)) 
			throw new Exception("Customer with "+ id + " doesn't exists");
		
		//Izveido data jpa funkciju, kurai uzleik @Query anotaciju, jo sql vaicajummu jataisa pasam IProductRepo 1.seminara
		float result = parcelRepo.calculateIncomeCustomerAsPersonById(id) + 
				parcelRepo.calculateIncomeCustomerAsCompanyById(id);
		
		//Ja rezultats ir 0
		if(result == 0) throw new Exception("There is no parcels!");
		
		//Ja viss ir kartiba, atgriezam so summu
		return result;
	}

	//PĀRBAUDĪTS
	@Override
	public int calculateHowManyParcelsNeedToDeliverToday() throws Exception {
		//Dabūju šodienas datumu yyyy-mm-dd
		int todayYear = LocalDateTime.now().getYear();
		int todayMonth = LocalDateTime.now().getMonthValue();
		int todayDay = LocalDateTime.now().getDayOfMonth();
		
		//Izveidoju vaicājuma String vērtību
		//TODO šeit varētu būt kļūda
		String sintax = "'" +todayYear + "-" + todayMonth + "-" + todayDay + "'%";
		
		//Nodefinē rezultatu
		int result = parcelRepo.countOfDeliveryForToday(sintax);
		
		//Atgriež rezultatu
		return result;
	}
	


}
