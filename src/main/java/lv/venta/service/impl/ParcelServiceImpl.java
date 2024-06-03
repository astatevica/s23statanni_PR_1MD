package lv.venta.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import lv.venta.model.City;
import lv.venta.model.Parcel;
import lv.venta.repo.ICustomerAsCompanyRepo;
import lv.venta.repo.ICustomerAsPersonRepo;
import lv.venta.repo.IParcelRepo;
import lv.venta.service.IParcelService;

public class ParcelServiceImpl implements IParcelService{
	
	@Autowired
	private IParcelRepo parcelRepo;
	
	@Autowired
	private ICustomerAsCompanyRepo customerAsCompanyRepo;
	
	@Autowired
	private ICustomerAsPersonRepo customerAsPersonRepo;

	@Override
	public ArrayList<Parcel> selectAllParcelsByCustomerId(int id) throws Exception {
		if(id < 0) throw new Exception("Id should be positive number!");
		
	}

	@Override
	public ArrayList<Parcel> selectAllParcelsDeliveredByDriverId(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Parcel> selectAllParcelsPriceLessThan(float price) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Parcel> selectAllParcelsDeliveredToCity(City city) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertNewParcelByCustomerCodeAndDriverId(String customer_code, int id) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeParcelDriverByParcelIdAndDriverId(int idp, int idd) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public float calculateIncomeOfParcelsByCustomerId(int id) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int calculateHowManyParcelsNeedToDeliverToday() throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}
