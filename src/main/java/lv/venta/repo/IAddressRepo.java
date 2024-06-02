package lv.venta.repo;

import org.springframework.data.repository.CrudRepository;

import lv.venta.model.Address;

public interface IAddressRepo extends CrudRepository<Address, Integer>{

}
