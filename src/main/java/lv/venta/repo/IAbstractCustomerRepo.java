package lv.venta.repo;

import org.springframework.data.repository.CrudRepository;

import lv.venta.model.AbstractCustomer;
import lv.venta.model.Person;

public interface IAbstractCustomerRepo extends CrudRepository<AbstractCustomer, Integer>{

	//Atradīs personu pēc personas koda
	Person findByCustomerAsPersonPersonPersonCode(String person_code);

	//Sameklē personu pēc personas koda
	boolean existsByCustomerAsPersonPersonPersonCode(String person_code);

	//Sameklē company pēc customer koda
	boolean existsByCustomerAsCompanyCompanyRegNo(String company_reg_no);


}
