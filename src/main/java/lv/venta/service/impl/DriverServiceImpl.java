package lv.venta.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import lv.venta.model.Driver;
import lv.venta.repo.IDriverRepo;
import lv.venta.service.IDriverCRUDService;

public class DriverServiceImpl implements IDriverCRUDService{

	@Autowired
	private IDriverRepo driverRepo;
	
	@Override
	public ArrayList<Driver> retrieveAll() throws Exception {
		//izmest izņēmumu, ja ir tukša tabula
		if(driverRepo.count()==0) throw new Exception("Database is empty");
		
		//pretējā gadīujumā sameklēt visus ierakstus no repo
		return (ArrayList<Driver>) driverRepo.findAll();
	}

	@Override
	public Driver retrieveById(int id) throws Exception {
		if(id < 0) throw new Exception("Id should be positive");
		
		if(driverRepo.existsById(id)) {
			return driverRepo.findById(id).get();
		}else {
			throw new Exception("Driver with this id ("+ id + ") is not in system");
		}
	}

	@Override
	public void deleteById(int Id) throws Exception {
		//atrast driver kuru gribam dzēst
		Driver driverForDeleting = retrieveById(Id);
		
		//dzēšam no repo un DB
		driverRepo.delete(driverForDeleting);
		
	}

	@Override
	public void create(Driver driver) throws Exception {
		//TODO Ar person_code varētu būt problēmas
		Driver existedDriver = driverRepo.findByNameAndSurnameAndPersonCode(driver.getName(), driver.getSurname(), driver.getPersonCode());
		
		//tāds driver jau eksistē
		if(existedDriver != null) throw new Exception("Driver with name: " + driver.getName() + " and person code: " + driver.getPersonCode() + " already exists in DB!");
		
		//tāds driver vēl neeksistē
		driverRepo.save(driver);
		
	}

	@Override
	public void update(int id, Driver driver) throws Exception {
		//atrodu
		Driver driverForUpdating = retrieveById(id);
		
		//izmainu
		driverForUpdating.setName(driver.getName());
		driverForUpdating.setSurname(driver.getSurname());
		driverForUpdating.setPersonCode(driver.getPersonCode());
		driverForUpdating.setLicense_no(driver.getLicense_no());
		driverForUpdating.setExperience_in_years(driver.getExperience_in_years());
		
		//saglabāju repo un DB
		driverRepo.save(driverForUpdating);
		
	}

}
