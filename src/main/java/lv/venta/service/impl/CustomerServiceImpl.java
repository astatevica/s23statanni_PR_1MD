package lv.venta.service.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.model.AbstractCustomer;
import lv.venta.model.Address;
import lv.venta.model.CustomerAsCompany;
import lv.venta.model.CustomerAsPerson;
import lv.venta.repo.IAbstractCustomerRepo;
import lv.venta.repo.ICustomerAsCompanyRepo;
import lv.venta.repo.ICustomerAsPersonRepo;
import lv.venta.service.ICustomerService;

@Service
public class CustomerServiceImpl implements ICustomerService{
	
	@Autowired
	private IAbstractCustomerRepo abstCustRepo;
	
	@Autowired
	private ICustomerAsPersonRepo custPersRepo;
	
	@Autowired
	private ICustomerAsCompanyRepo custCompRepo;

	@Override
	public void insertNewCustomerAsPerson(String person_code) throws Exception {
		//pārbaudu vai tāds jau eksistē
		if(abstCustRepo.existsByCustomerAsPersonPersonPersonCode(person_code)) throw new Exception("Customer with person code: " + person_code + " already exists!");
		
		//pievienoju jaunu un saglabāju
		CustomerAsPerson customerAsPerson = new CustomerAsPerson(person_code, abstCustRepo.findByCustomerAsPersonPersonPersonCode(person_code));
		custPersRepo.save(customerAsPerson);
	}

	@Override
	public void insertNewCustomerAsCompany(String company_reg_no, String title) throws Exception {
		//pārbaudu vai tāds jau eksistē
		if(abstCustRepo.existsByCustomerAsCompanyCompanyRegNo(company_reg_no)) throw new Exception("Customer with company registration number: " + company_reg_no + " already exists!");
		
		//pievienoju jaunu un saglabāju
		CustomerAsCompany customerAsCompany = new CustomerAsCompany(company_reg_no, title);
		custCompRepo.save(customerAsCompany);
	}

	@Override
	public void addAddressToCustomerByCustomerId(int id, Address address) throws Exception {
		//pārbaudu vai tāds customers eksistē
		if(!abstCustRepo.existsById(id)) throw new Exception("Customer with id: " + id + " does not exist");
		
		//sameklēju customeri, kuram vajag pievienot adresi
		AbstractCustomer customerToAddAddress = abstCustRepo.findById(id).get();
		
		//pievienoju adresi un saglabāju
		customerToAddAddress.setAddress(address);
		abstCustRepo.save(customerToAddAddress);
		
	}

}
