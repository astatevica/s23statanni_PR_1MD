package lv.venta.repo;

import org.springframework.data.repository.CrudRepository;

import lv.venta.model.CustomerAsCompany;
import lv.venta.model.CustomerAsPerson;

public interface ICustomerAsCompanyRepo extends CrudRepository<CustomerAsCompany, Integer>{

	boolean existsByCustomer_code(String customer_code);

	CustomerAsPerson findByCustomer_code(String customer_code);

}
