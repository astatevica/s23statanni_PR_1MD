package lv.venta.repo;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import lv.venta.model.City;
import lv.venta.model.Parcel;

public interface IParcelRepo extends CrudRepository<Parcel, Integer>{
	
	//atradīs customer with id
	ArrayList<Parcel> findByAbstractCustomerIdac(int id);

	//atradīs parcel by drivers id
	ArrayList<Parcel> findByDriverIdd(int id);

	//atradīs preci kas lētāka par ievadīto skaitli
	ArrayList<Parcel> findByPriceLessThan(float price);

	//atradīs preci kas jāpiegādā konkrētai pilsētai no pasutītāja
	ArrayList<Parcel> findByAbstractCustomerAddressCity(City city);
	
	//atradīs customer person with id un tālāk dosies un nākamo tabulu meklēs customer_code
	ArrayList<Parcel> findByAbstractCustomerCustomerAsCompanyCustomerCode(String customer_code);
	
	//atradīs customer company with id un tālāk dosies un nākamo tabulu meklēs customer_code
	ArrayList<Parcel> findByAbstractCustomerCustomerAsPersonCustomerCode(String customer_code);
	
	//Query db lai saskaitītu cutomer person ieņēmumus
	@Query(nativeQuery = true, value = "SELECT sum(price) FROM parcel WHERE idac=(?1);")
	float calculateIncomeCustomerById(int idac);
	
	
	//Query, kas paņems datumu no DB
	@Query(nativeQuery = true, value = "select count(idpa) from parcel where order_delivery like (?1);")
	int countOfDeliveryForToday(String sintax);



	
	

}
