package lv.venta.repo;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import lv.venta.model.City;
import lv.venta.model.Parcel;

public interface IParcelRepo extends CrudRepository<Parcel, Integer>{
	
	//atradīs customer person with id
	ArrayList<Parcel> findByIdcp(int idcp);
	
	//atradīs customer company with id
	ArrayList<Parcel> findByIdcc(int idcc);

	//atradīs parcel by drivers id
	ArrayList<Parcel> findByIdd(int id);

	//atradīs preci kas lētāka par ievadīto skaitli
	ArrayList<Parcel> findByPriceLessThan(float price);

	//TODO šis varētu būt problemātisks
	//atradīs preci kas jāpiegādā konkrētai pilsētai
	ArrayList<Parcel> findByIdccIdaCity(City city);
	
	//atradīs customer person with id un tālāk dosies un nākamo tabulu meklēs customer_code
	ArrayList<Parcel> findByIdcpCustomer_code(String customer_code);
	
	//atradīs customer company with id un tālāk dosies un nākamo tabulu meklēs customer_code
	ArrayList<Parcel> findByIdccCustomer_code(String customer_code);

	//atradīs pašu parcel by Id
	boolean existsByIdp(int idp);
	
	

}
