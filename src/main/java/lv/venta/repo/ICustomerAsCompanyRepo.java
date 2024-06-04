package lv.venta.repo;

import org.springframework.data.repository.CrudRepository;

import lv.venta.model.CustomerAsCompany;

public interface ICustomerAsCompanyRepo extends CrudRepository<CustomerAsCompany, Integer>{

	boolean existsByCustomerCode(String customer_code);

	CustomerAsCompany findByCustomerCode(String customer_code);

}
