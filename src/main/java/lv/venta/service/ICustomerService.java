package lv.venta.service;

import lv.venta.model.Address;

public interface ICustomerService {

	//insertNewCustomerAsPerson- pievieno sistēmā jaunu pircēju kā personu, bet nepievienojot adresi. 
		//Neaizmirstam pārbaudīt, vai šāds pircējs jau neeksistē.
	public abstract void insertNewCustomerAsPerson(String person_code) throws Exception;

	//insertNewCustomerAsCompany- pievieno sistēmā jaunu pircēju kā kompāniju, bet nepievienojot adresi. Neaizmirstam
		//pārbaudīt, vai šāda kompānija jau neeksistē.
	public abstract void insertNewCustomerAsCompany(String company_reg_no, String title) throws Exception;
	  
	//addAddressToCustomerByCustomerId- izveido un pievieno konkrētajam pircējam adresi.
	public abstract void addAddressToCustomerByCustomerId(int id, Address address) throws Exception;
	
}
