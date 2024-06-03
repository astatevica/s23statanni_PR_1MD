package lv.venta.service;

import java.util.ArrayList;

import lv.venta.model.Driver;

public interface IDriverCRUDService {
	//CRUD - create - retrieve - update - delete
	
	//selectAllDriver- atgriež visus šoferus, kas ir saglabāti sistēmā
	public abstract ArrayList<Driver> retrieveAll() throws Exception;
	
	//selectDriverById- atgriež vienu šoferi pēc tā id
	public abstract Driver retrieveById(int id) throws Exception;
	
	//deleteDriverById- dzēš šoferi pēc tā id
	public abstract void deleteById(int Id) throws Exception;
	
	//insertNewDriver- pievieno jaunu šoferi sistēmā
	public abstract void create(Driver driver);
	
	//updateDriverById- rediģē esošo šoferi
	public abstract void update(int id, Driver driver) throws Exception;

}
