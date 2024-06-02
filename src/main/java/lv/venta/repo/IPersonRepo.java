package lv.venta.repo;

import org.springframework.data.repository.CrudRepository;

import lv.venta.model.Person;

public interface IPersonRepo extends CrudRepository<Person, Integer>{

}
