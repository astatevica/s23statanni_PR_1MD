package lv.venta;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataIntegrityViolationException;

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
	public CommandLineRunner testDB(IAddressRepo addrRepo, ICustomerAsCompanyRepo custCompRepo, ICustomerAsPersonRepo custPersRepo, 
			IDriverRepo driverRepo, IParcelRepo parcelRepo,IPersonRepo personRepo, IAbstractCustomerRepo abstractRepo) {
		
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
				Address address4 = new Address(City.Ventspils,13,"Kuldīgas iela");
				Address address5 = new Address(City.Riga,7,"Ventspils iela");
				Address address6 = new Address(City.Liepaja,71,"Rīgas iela");
				addrRepo.save(address1);
				addrRepo.save(address2);
				addrRepo.save(address3);
				addrRepo.save(address4);
				addrRepo.save(address5);
				addrRepo.save(address6);
				
				//Driver Model pārbaude WORKS
				Driver driver1 = new Driver("Undīne", "240601-84393","Tīruma",(float)1.5,"AT187293");
				Driver driver2 = new Driver("Vitauts", "240276-97203","Strautmanis",(float)15.5,"AT982013");
				Driver driver3 = new Driver("Valts", "130991-98291","Krauklis",(float)7.3,"AT358423");
				Driver driver4 = new Driver("Laila", "287394-84393","Vanaga",(float)1.8,"AT198273");
				Driver driver5 = new Driver("Valdis", "378477-97203","Laipa",(float)10.5,"AT276354");
				Driver driver6 = new Driver("Justs", "347625-98291","Kurzemnieks",(float)9.3,"AT198276");
				driverRepo.save(driver1);
				driverRepo.save(driver2);
				driverRepo.save(driver3);
				driverRepo.save(driver4);
				driverRepo.save(driver5);
				driverRepo.save(driver6);
				
				
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
				
				//AbstractCustomer pārbaude
				//String phone_no, Address address, CustomerAsCompany customerAsCompany, CustomerAsPerson customerAsPerson
				AbstractCustomer abCustomer1 = new AbstractCustomer("98201982", address6,null,customerAsPerson3);
				AbstractCustomer abCustomer2 = new AbstractCustomer("67398746", address6,null,customerAsPerson1);
				AbstractCustomer abCustomer3 = new AbstractCustomer("53682763", address4,null,customerAsPerson2);
				AbstractCustomer abCustomer4 = new AbstractCustomer("26538764", address3,customerAsCompany3,null);
				AbstractCustomer abCustomer5 = new AbstractCustomer("16527632", address2,customerAsCompany2,null);
				AbstractCustomer abCustomer6 = new AbstractCustomer("18726532", address1,customerAsCompany1,null);
				try {
					abstractRepo.save(abCustomer1);
					abstractRepo.save(abCustomer2);
					abstractRepo.save(abCustomer3);
					abstractRepo.save(abCustomer4);
					abstractRepo.save(abCustomer5);
					abstractRepo.save(abCustomer6);
				} catch (DataIntegrityViolationException e) {
				    System.out.println("history already exist");
				}
				
				//Parcel Model pārbaude WORKS
				//boolean is_fragile, int days, float price, Size size, AbstractCustomer abstractCustomer, Driver driver
				Parcel parcel1 = new Parcel(false,2,(float)12.99, Size.M,abCustomer1, driver1);
				Parcel parcel2 = new Parcel(true,5,(float)70.00, Size.L,abCustomer2, driver2);
				Parcel parcel3 = new Parcel(false,7,(float)17.45, Size.S,abCustomer3, driver3);
				Parcel parcel4 = new Parcel(false ,3,(float)2.99, Size.S,abCustomer3, driver1);
				Parcel parcel5 = new Parcel(false ,4,(float)2.99, Size.S,abCustomer5, driver1);
				Parcel parcel6 = new Parcel(false ,2,(float)2.99, Size.S,abCustomer6, driver6);

				try {
					parcelRepo.save(parcel1);
					parcelRepo.save(parcel2);
					parcelRepo.save(parcel3);
					parcelRepo.save(parcel4);
					parcelRepo.save(parcel5);
					parcelRepo.save(parcel6);
				} catch (DataIntegrityViolationException e) {
				    System.out.println("history already exist");
				}
				
				
			}
		};
	}

}
