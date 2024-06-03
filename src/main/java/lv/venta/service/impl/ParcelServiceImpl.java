package lv.venta.service.impl;

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

	@Override
	public ArrayList<Parcel> selectAllParcelsByCustomerId(int id) throws Exception {
		if(id < 0) throw new Exception("Id should be positive number!");
		
		if(!customerAsCompanyRepo.existsById(id) || !customerAsPersonRepo.existsById(id)) throw new Exception("Customer with " + id + " id doesn't exist");
		//TODO šeit var labāk uzlabot
		ArrayList<Parcel> result = parcelRepo.findByIdcp(id);
		result.addAll(parcelRepo.findByIdcc(id));
		
		if(result.isEmpty()) throw new Exception("There is no parcel with id: " + id + " as customer");
		
		return result;
	}

	@Override
	public ArrayList<Parcel> selectAllParcelsDeliveredByDriverId(int id) throws Exception {
		if(id < 0) throw new Exception("Id should be positive number!");
		
		ArrayList<Parcel> result = parcelRepo.findByIdd(id);
		
		if(result.isEmpty()) throw new Exception("There is no parcel with id: " + id + " drivers");
		
		return result;
	}

	@Override
	public ArrayList<Parcel> selectAllParcelsPriceLessThan(float price) throws Exception {
		if(price < 0.0) throw new Exception("Price should be more than zero!");
		
		ArrayList<Parcel> result = parcelRepo.findByPriceLessThan(price);
		
		if(result.isEmpty()) throw new Exception("There is no parcel with price less than " + price);
		
		return result;
	}

	@Override
	public ArrayList<Parcel> selectAllParcelsDeliveredToCity(City city) throws Exception {
		//Nepārbaudu city, jo tas ir enumerators
		ArrayList<Parcel> result = parcelRepo.findByIdccIdaCity(city);
		
		if(result.isEmpty()) throw new Exception("There is no order to city " + city);
		
		return result;
	}

	//TODO NESTRĀDĀ
	@Override
	public void insertNewParcelByCustomerCodeAndDriverId(String customer_code, int id) throws Exception {

		//TODO man te kks nestrādā
//		//pārbauda vai tādi customer code eksistē
//		if(!customerAsCompanyRepo.existsByCustomer_code(customer_code) || !customerAsPersonRepo.existsByCustomer_code(customer_code)) 
//			throw new Exception("Customer with " + customer_code + " customer code doesn't exist");
//		
//		//pārbaudu vai tāds driver eksistē
//		if(!driverRepo.existsByIdd(id)) throw new Exception("Driver with " + id + " id doesn't exist");
//		
//		CustomerAsPerson person = customerAsPersonRepo.findByCustomer_code(customer_code);
//		CustomerAsCompany company = customerAsCompanyRepo.existsByCustomer_code(customer_code);
//		Driver driver = driverRepo.findById(id);
//		
//		if(person.equals(null) && !company.equals(null)) {
//			Parcel parcel = new Parcel(false,2,(float)12.99, Size.M,person, null, driver);
//			parcelRepo.save(parcel);
//		}else{
//			Parcel parcel = new Parcel(false,2,(float)12.99, Size.M,null, company, driver);
//			parcelRepo.save(parcel);
//		}
		
	}

	@Override
	public void changeParcelDriverByParcelIdAndDriverId(int idp, int idd) throws Exception {
		if(idp < 0) throw new Exception("Parcels Id should be positive!");
		//atrodu parcel
		Parcel parcelForUpdating = retriveById(idp);
		if(parcelForUpdating.equals(null)) throw new Exception("Parcel with Id does not exists!");
		
		if(idd < 0) throw new Exception("Drivers Id should be positive!");
		if(driverRepo.findByIdd(idd).equals(null)) throw new Exception("Driver with Id does not exists!");
		//izmainu
		parcelForUpdating.setDriver(driverRepo.findByIdd(idd));
		
		//saglabāju repo un DB
		parcelRepo.save(parcelForUpdating);
	}

	private Parcel retriveById(int idp) throws Exception {
		if(idp < 0) throw new Exception("Id should be positive");
		
		if(parcelRepo.existsByIdp(idp)) {
			return parcelRepo.findById(idp).get();
		}else {
			throw new Exception("Product with this id ("+ idp + ") is not in system");
		}
	}

	@Override
	public float calculateIncomeOfParcelsByCustomerId(int id) throws Exception {
		//TODO te vajadzēs veidot ParcelRepo query
		return 0;
	}

	@Override
	public int calculateHowManyParcelsNeedToDeliverToday() throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	


}
