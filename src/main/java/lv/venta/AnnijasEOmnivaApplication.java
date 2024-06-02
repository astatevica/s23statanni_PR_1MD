package lv.venta;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import lv.venta.model.Person;
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
			IParcelRepo parcelRepo,IPersonRepo personRepo) {
		
		return new CommandLineRunner() {
			
			@Override
			public void run(String... args) throws Exception {
				//Person Model pārbaude
				Person person1 = new Person("Anna", "290344-32123", "Kalniņa");
				Person person2 = new Person("Jānis", "290867-32123", "Pērkons");
				Person person3 = new Person("Laila", "130767-32123", "Mākone");
				personRepo.save(person1);
				personRepo.save(person2);
				personRepo.save(person3);
				
				
			}
		};
	}

}
