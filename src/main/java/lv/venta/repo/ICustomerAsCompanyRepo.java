package lv.venta.repo;

import org.springframework.data.repository.CrudRepository;

import lv.venta.model.CustomerAsCompany;

public interface ICustomerAsCompanyRepo extends CrudRepository<CustomerAsCompany, Integer>{

	boolean existsByCustomer_code(String customer_code);

	CustomerAsCompany findByCustomer_code(String customer_code);

}
