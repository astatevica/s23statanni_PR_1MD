package lv.venta.repo;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
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
	//atradīs preci kas jāpiegādā konkrētai pilsētai no kompānijas pasutītāja
	ArrayList<Parcel> findByIdccIdaCity(City city);
	
	//atradīs preci kas jāpiegādā konkrētai pilsētai no personas pasutītāja
	ArrayList<Parcel> findByIdcpIdaCity(City city);
	
	//atradīs customer person with id un tālāk dosies un nākamo tabulu meklēs customer_code
	ArrayList<Parcel> findByIdcpCustomer_code(String customer_code);
	
	//atradīs customer company with id un tālāk dosies un nākamo tabulu meklēs customer_code
	ArrayList<Parcel> findByIdccCustomer_code(String customer_code);

	//atradīs pašu parcel by Id
	boolean existsByIdp(int idp);
	
	//Query db lai saskaitītu cutomer person ieņēmumus
	@Query(nativeQuery = true, value = "SELECT sum(price) FROM parcel WHERE idcp=(?1);")
	float calculateIncomeCustomerAsPersonById(int id);
	
	//Query db lai saskaitītu cutomer company ieņēmumus
	@Query(nativeQuery = true, value = "SELECT sum(price) FROM parcel WHERE idcc=(?1);")
	float calculateIncomeCustomerAsCompanyById(int id);
	
	//Query, kas paņems datumu no DB
	@Query(nativeQuery = true, value = "select count(idpa) from parcel where order_delivery like (?1);")
	int countOfDeliveryForToday(String sintax);
	
	

}
