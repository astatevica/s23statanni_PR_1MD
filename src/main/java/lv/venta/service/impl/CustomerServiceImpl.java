package lv.venta.service.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.model.AbstractCustomer;
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
	public void insertNewCustomerAsPerson(CustomerAsPerson customerAsPerson) throws Exception {
		//person_code
		//pārbaudu vai tāds jau eksistē
		if(custPersRepo.existsByPersonCode(customerAsPerson.getPersonCode()))
			throw new Exception("Customer with person code: " + customerAsPerson.getPersonCode() + " already exists!");
		
		//pievienoju jaunu un saglabāju
		CustomerAsPerson newPerson = new CustomerAsPerson(customerAsPerson.getPersonCode(), customerAsPerson.getPerson());
		custPersRepo.save(newPerson);
	}

	@Override
	public void insertNewCustomerAsCompany(CustomerAsCompany customerAsCompany) throws Exception {
		//pārbaudu vai tāds jau eksistē
		//šo var optimizēt
		if(abstCustRepo.existsByCustomerAsCompanyCompanyRegNo(customerAsCompany.getCompanyRegNo())) throw new Exception("Customer with company registration number: " + customerAsCompany.getCompanyRegNo() + " already exists!");
		
		//pievienoju jaunu un saglabāju
		CustomerAsCompany newCompnay = new CustomerAsCompany(customerAsCompany.getCompanyRegNo(), customerAsCompany.getTitle());
		custCompRepo.save(newCompnay);
	}

	@Override
	public void addAddressToCustomerByCustomerId(int id, AbstractCustomer abstractCustomer) throws Exception {
		//sameklēju customeri, kuram vajag pievienot adresi
		AbstractCustomer customerToAddAddress = retrieveById(id);
		
		//pievienoju adresi un saglabāju
		customerToAddAddress.setAddress(abstractCustomer.getAddress());
		abstCustRepo.save(customerToAddAddress);
		
	}

	@Override
	public AbstractCustomer retrieveById(int id) throws Exception {
		if(id < 0) throw new Exception("Id should be positive");
		
		if(abstCustRepo.existsById(id)) {
			return abstCustRepo.findById(id).get();
		}else {
			throw new Exception("Driver with this id ("+ id + ") is not in system");
		}
	}
	
	

}
