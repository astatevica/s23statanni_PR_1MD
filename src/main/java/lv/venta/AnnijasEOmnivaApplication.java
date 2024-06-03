package lv.venta;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import lv.venta.model.AbstractCustomer;
import lv.venta.model.Address;
import lv.venta.model.City;
import lv.venta.model.CustomerAsCompany;
import lv.venta.model.CustomerAsPerson;
import lv.venta.model.Driver;
import lv.venta.model.Parcel;
import lv.venta.model.Person;
import lv.venta.model.Size;
import lv.venta.repo.IAbstractCustomerRepo;
import lv.venta.repo.IAddressRepo;
import lv.venta.repo.ICustomerAsCompanyRepo;
import lv.venta.repo.ICustomerAsPersonRepo;
import lv.venta.repo.IDriverRepo;
import lv.venta.repo.IParcelRepo;
import lv.venta.repo.IPersonRepo;

@SpringBootApplication
public class AnnijasEOmnivaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnnijasEOmnivaApplication.class, args);
	}
	
	@Bean //izsaucas automātiski, kas palaiž sistēmu
	public CommandLineRunner testDB(IAddressRepo addrRepo, ICustomerAsCompanyRepo custCompRepo, ICustomerAsPersonRepo custPersRepo, IDriverRepo driverRepo, 
			IParcelRepo parcelRepo,IPersonRepo personRepo, IAbstractCustomerRepo abstractRepo) {
		
		return new CommandLineRunner() {
			
			@Override
			public void run(String... args) throws Exception {
				//Person Model pārbaude WORKS
				Person person1 = new Person("Anna", "290344-32123", "Kalniņa");
				Person person2 = new Person("Jānis", "290867-32123", "Pērkons");
				Person person3 = new Person("Laila", "130767-32123", "Mākone");
				personRepo.save(person1);
				personRepo.save(person2);
				personRepo.save(person3);
				
				//Address Model pārbaude WORKS
				Address address1 = new Address(City.Ventspils,12,"Kuldīgas iela");
				Address address2 = new Address(City.Riga,5,"Ventspils iela");
				Address address3 = new Address(City.Liepaja,77,"Rīgas iela");
				addrRepo.save(address1);
				addrRepo.save(address2);
				addrRepo.save(address3);
				
				//Driver Model pārbaude WORKS
				Driver driver1 = new Driver("Undīne", "240601-84393","Tīruma",(float)1.5,"AT187293");
				Driver driver2 = new Driver("Vitauts", "240276-97203","Strautmanis",(float)15.5,"AT982013");
				Driver driver3 = new Driver("Valts", "130991-98291","Krauklis",(float)7.3,"AT358423");
				driverRepo.save(driver1);
				driverRepo.save(driver2);
				driverRepo.save(driver3);
				
				//CustomerAsPerson Model pārbaude WORKS
				CustomerAsPerson customerAsPerson1 = new CustomerAsPerson("290344-32123", person1);
				CustomerAsPerson customerAsPerson2 = new CustomerAsPerson("290867-32123", person2);
				CustomerAsPerson customerAsPerson3 = new CustomerAsPerson("130767-32123", person3);
				custPersRepo.save(customerAsPerson1);
				custPersRepo.save(customerAsPerson2);
				custPersRepo.save(customerAsPerson3);
				
				//CustomerAsCompany Model pārbaude WORKS
				CustomerAsCompany customerAsCompany1 = new CustomerAsCompany("LV19820938762", "SIA Ābolītis");
				CustomerAsCompany customerAsCompany2 = new CustomerAsCompany("LV90387265361", "SIA Strautiņš");
				CustomerAsCompany customerAsCompany3 = new CustomerAsCompany("LV27817625363", "SIA Vanagi");
				custCompRepo.save(customerAsCompany1);
				custCompRepo.save(customerAsCompany2);
				custCompRepo.save(customerAsCompany3);
				
				//Parcel Model pārbaude WORKS
				Parcel parcel1 = new Parcel(false,2,(float)12.99, Size.M,customerAsPerson1, null, driver1);
				Parcel parcel2 = new Parcel(true ,6,(float)2.99, Size.S,null, customerAsCompany2, driver2);
				parcelRepo.save(parcel1);
				parcelRepo.save(parcel2);
				
				//AbstractCustomer pārbaude
				//String phone_no, Address address, CustomerAsCompany customerAsCompany, CustomerAsPerson customerAsPerson
				AbstractCustomer abCustomer1 = new AbstractCustomer("98201982", address1,null,customerAsPerson1);
				AbstractCustomer abCustomer2 = new AbstractCustomer("67398746", address2,null,customerAsPerson2);
				AbstractCustomer abCustomer3 = new AbstractCustomer("53682763", address3,null,customerAsPerson3);
				AbstractCustomer abCustomer4 = new AbstractCustomer("26538764", address1,customerAsCompany1,null);
				AbstractCustomer abCustomer5 = new AbstractCustomer("16527632", address2,customerAsCompany2,null);
				AbstractCustomer abCustomer6 = new AbstractCustomer("18726532", address3,customerAsCompany3,null);
				abstractRepo.save(abCustomer1);
				abstractRepo.save(abCustomer2);
				abstractRepo.save(abCustomer3);
				abstractRepo.save(abCustomer4);
				abstractRepo.save(abCustomer5);
				abstractRepo.save(abCustomer6);

				
				
			}
		};
	}

}
