package lv.venta.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import lv.venta.model.AbstractCustomer;
import lv.venta.model.City;
import lv.venta.model.Parcel;
import lv.venta.repo.IAbstractCustomerRepo;
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
	
	@Autowired
	private IAbstractCustomerRepo abstractCustRepo;

	//DOUBLE CHECKED
	@Override
	public ArrayList<Parcel> selectAllParcelsByCustomerId(int id) throws Exception {
		//pārbaudu id vērtību
		if(id < 0) throw new Exception("Id should be positive number!");
		
		//pārbaudu vai eksistē šādi customers
		if(!abstractCustRepo.existsById(id)) throw new Exception("Customer with " + id + " id doesn't exist");
		//Salieku visus vienā ArrayListā
		ArrayList<Parcel> result = parcelRepo.findByAbstractCustomerIdac(id);
		
		//Pārbaudu vai nav tukšs lists
		if(result.isEmpty()) throw new Exception("There is no parcel with id: " + id + " as customer");
		
		//atgriežu rezultātu
		return result;
	}

	//DOUBLE CHECKED
	@Override
	public ArrayList<Parcel> selectAllParcelsDeliveredByDriverId(int id) throws Exception {
		//Pārbaudu id vērtību
		if(id < 0) throw new Exception("Id should be positive number!");
		
		//Atrodu Drivers pēc id un ielieku listā 
		ArrayList<Parcel> result = parcelRepo.findByDriverIdd(id);
		
		//Pārbaudu vai lists nav tukšs
		if(result.isEmpty()) throw new Exception("There is no parcel with id: " + id + " drivers");
		
		return result;
	}

	//DOUBLE CHECKED
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

	//DOUBLE CHECKED
	@Override
	public ArrayList<Parcel> selectAllParcelsDeliveredToCity(City city) throws Exception {
		//Nepārbaudu city, jo tas ir enumerators
		
		//Meklēju adresi un pilsētu
		ArrayList<Parcel> result = parcelRepo.findByAbstractCustomerAddressCity(city);
		
		//Pārbaudu vai lists nav tukšs
		if(result.isEmpty()) throw new Exception("There is no order to city " + city);
		
		//atgriežu rezultātu
		return result;
	}

	//DOUBLE CHECKED
	@Override
	public void insertNewParcelByCustomerCodeAndDriverId(String customer_code, int id) throws Exception {

		//atrodu customer company pēc id
		if(customerAsCompanyRepo.findByCustomerCode(customer_code).equals(null) ||
				customerAsPersonRepo.findByCustomerCode(customer_code).equals(null)) throw new 
		Exception("Customer with following customer code: " + customer_code + " does not exists!");
		
		//atrodu driver by id
		if(driverRepo.findById(id).equals(null)) throw new 
		Exception("Driver with following id: " + id + " does not exists!");
		
		//Izveidoju noklusējuma Parcel
		Parcel newParcel = new Parcel();
		
		//Izveidoju noklusējuma AbstractCustomer
		AbstractCustomer newCustomer = new AbstractCustomer();
		
		//Uzlieku jaunās vērtības
		newCustomer.setCustomerAsCompany(customerAsCompanyRepo.findByCustomerCode(customer_code));
		newCustomer.setCustomerAsPerson(customerAsPersonRepo.findByCustomerCode(customer_code));
		newParcel.setAbstractCustomer(newCustomer);
		newParcel.setDriver(driverRepo.findByIdd(id));
		
		//Saglabāju DB un repozitorijā
		parcelRepo.save(newParcel);
		
	}

	//DOUBLE CHECKED
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

	//DOUBLE CHECKED
	@Override
	public float calculateIncomeOfParcelsByCustomerId(int id) throws Exception {
		//Pārbauda id vai nav negativs
		if(id < 1) throw new Exception("Id should be positive");
		
		//Parbauda vai tads kurs ar id vispar eksistee
		if(!abstractCustRepo.existsById(id)) 
			throw new Exception("Customer with "+ id + " doesn't exists");
		
		//Izveido data jpa funkciju, kurai uzleik @Query anotaciju, jo sql vaicajummu jataisa pasam IProductRepo 1.seminara
		float result = parcelRepo.calculateIncomeCustomerById(id);
		
		//Ja rezultats ir 0
		if(result == 0) throw new Exception("There is no parcels!");
		
		//Ja viss ir kartiba, atgriezam so summu
		return result;
	}

	//DOUBLE CHECKED
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
