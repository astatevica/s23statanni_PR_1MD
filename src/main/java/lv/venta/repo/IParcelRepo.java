package lv.venta.repo;

import org.springframework.data.repository.CrudRepository;

import lv.venta.model.Parcel;

public interface IParcelRepo extends CrudRepository<Parcel, Integer>{

}
