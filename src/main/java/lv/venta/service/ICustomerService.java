package lv.venta.service;


public interface ICustomerService {

	//insertNewCustomerAsPerson- pievieno sistēmā jaunu pircēju kā personu, bet nepievienojot adresi. 
		//Neaizmirstam pārbaudīt, vai šāds pircējs jau neeksistē.
	public abstract void insertNewCustomerAsPerson(String person_code, String phone_no) throws Exception;

	//insertNewCustomerAsCompany- pievieno sistēmā jaunu pircēju kā kompāniju, bet nepievienojot adresi. Neaizmirstam
		//pārbaudīt, vai šāda kompānija jau neeksistē.
	public abstract void insertNewCustomerAsCompany(String company_reg_no, String phone_no, String title) throws Exception;
	  
	//TODO jāizveido starptabula
	//addAddressToCustomerByCustomerId- izveido un pievieno konkrētajam pircējam adresi.
	public abstract void addAddressToCustomerByCustomerId(int id) throws Exception;
	
}
