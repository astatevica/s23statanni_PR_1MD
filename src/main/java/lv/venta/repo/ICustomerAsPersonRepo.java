package lv.venta.repo;

import org.springframework.data.repository.CrudRepository;

import lv.venta.model.CustomerAsPerson;

public interface ICustomerAsPersonRepo extends CrudRepository<CustomerAsPerson,Integer>{

//	boolean existsByCustomer_code(String customer_code);
//
//	CustomerAsPerson findByCustomer_code(String customer_code);

}
