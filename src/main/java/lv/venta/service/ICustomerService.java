package lv.venta.service;

import java.util.ArrayList;

import lv.venta.model.AbstractCustomer;
import lv.venta.model.CustomerAsCompany;
import lv.venta.model.CustomerAsPerson;

public interface ICustomerService {

	//insertNewCustomerAsPerson- pievieno sistēmā jaunu pircēju kā personu, bet nepievienojot adresi. 
		//Neaizmirstam pārbaudīt, vai šāds pircējs jau neeksistē.
	public abstract void insertNewCustomerAsPerson(CustomerAsPerson customerAsPerson) throws Exception;

	//insertNewCustomerAsCompany- pievieno sistēmā jaunu pircēju kā kompāniju, bet nepievienojot adresi. Neaizmirstam
		//pārbaudīt, vai šāda kompānija jau neeksistē.
	public abstract void insertNewCustomerAsCompany(CustomerAsCompany customerAsCompany) throws Exception;
	  
	//addAddressToCustomerByCustomerId- izveido un pievieno konkrētajam pircējam adresi.
	public abstract void addAddressToCustomerByCustomerId(int id, AbstractCustomer abstractCustomer) throws Exception;

	//iegūstu abstract suctomer by id
	public abstract AbstractCustomer retrieveById(int id) throws Exception;

	//parādīt visus customers
	public abstract ArrayList<AbstractCustomer> retrieveAll() throws Exception;
	
}
