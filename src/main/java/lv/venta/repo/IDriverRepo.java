package lv.venta.repo;

import org.springframework.data.repository.CrudRepository;

import lv.venta.model.Driver;

public interface IDriverRepo extends CrudRepository<Driver, Integer>{

	//TODO te var būt problēma ar Person_code
	Driver findByNameAndSurnameAndPersonCode(String name, String surname, String person_code);

	boolean existsByIdd(int id);

	Driver findByIdd(int idd);

}
