package lv.venta.service;

import java.util.ArrayList;

import lv.venta.model.City;
import lv.venta.model.Parcel;

public interface IParcelService {
	
	//selectAllParcelsByCustomerId- atgriež visas paciņas konkrētam pircējam;
	public abstract ArrayList<Parcel> selectAllParcelsByCustomerId(int id) throws Exception;
	
	//selectAllParcelsDeliveredByDriverId- atgriež visas paciņas, kuras ir piegādājis vai piegādās konkrēts šoferis;
	public abstract ArrayList<Parcel> selectAllParcelsDeliveredByDriverId(int id) throws Exception;
	
	//selectAllParcelsPriceLessThan- atgriež visas paciņas, kuras cena ir mazāka par padoto sliekšņa vērtību (piemēram, 5 eur);
	public abstract ArrayList<Parcel> selectAllParcelsPriceLessThan(float price) throws Exception;
	
	//selectAllParcelsDeliveredToCity- atgriež visas paciņas, kuras ir jānogādā (vai ir jau nogādātas) uz konkrētu pilsētu;
	public abstract ArrayList<Parcel> selectAllParcelsDeliveredToCity(City city) throws Exception;
	
	//insertNewParcelByCustomerCodeAndDriverId- pievieno jaunu paciņu konkrētam pircējam un piesaistot to konkrētam šoferim pēc tā id;
	public abstract void insertNewParcelByCustomerCodeAndDriverId(String customer_code, int id) throws Exception;
	
	//changeParcelDriverByParcelIdAndDriverId- nomaina esošās paciņas kurjeru uz citu šoferi, funkcijā padodot gan šofera id, gan paciņas id;
	public abstract void changeParcelDriverByParcelIdAndDriverId(int idp, int idd) throws Exception;
	 
	//calculateIncomeOfParcelsByCustomerId- aprēķina un atgriež kopējo paciņu summu konkrētajam pircējam, funkcijā padodot pircēja id;
	public abstract float calculateIncomeOfParcelsByCustomerId(int id) throws Exception;
	
	//calculateHowManyParcelsNeedToDeliverToday- atgriež paciņu kopskaitu, kuras jānogādā šodien.
	public abstract int calculateHowManyParcelsNeedToDeliverToday() throws Exception;
	

}
