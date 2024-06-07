package lv.venta.repo;

import org.springframework.data.repository.CrudRepository;

import lv.venta.model.CustomerAsPerson;

public interface ICustomerAsPersonRepo extends CrudRepository<CustomerAsPerson,Integer>{

	boolean existsByCustomerCode(String customer_code);

	CustomerAsPerson findByCustomerCode(String customer_code);

	boolean existsByPersonCode(String person_code);




}
