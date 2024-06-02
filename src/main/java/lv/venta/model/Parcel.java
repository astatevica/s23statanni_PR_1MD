package lv.venta.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "Parcel")
@Entity
public class Parcel {
	
	@Id
	@Column(name = "Idpa")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	private int idpa;
	
	@Column(name = "Is_fragile")
	@NotNull
	@Setter
	private boolean is_fragile;
	
	//TODO šeit var rasties priblēmas
	@Column(name = "Order_created")
	@NotNull
	@Setter
	private Date order_created;
	
	//TODO šeit var rasties priblēmas
	@Column(name = "Order_delivery")
	@NotNull
	@Setter
	private Date order_delivery;
	
	@Column(name = "Price")
	@NotNull
	@Setter
	@Min((long)0.1)
	private float price;
	
	@Column(name = "Size")
	@NotNull
	private Size size;
	
	
	//saite no CustomerAsPerson
	@OneToOne
	@JoinColumn(name = "Idc") //TODO iespējams vajadzēs pārsaukt
	private CustomerAsPerson customerAsPerson;
	
	//TODO saite uz CustomerAsCompany
	//TODO saite uz Driver
	
	public Parcel(boolean is_fragile,Date order_created, Date order_delivery, float price, Size size, CustomerAsPerson customerAsPerson) {
		set_fragile(is_fragile);
		setOrder_created(order_created);//TODO saprast kā pievienot order date
		setOrder_delivery(order_delivery);//TODO saprast kā pievienot order date
		setPrice(price);
		setSize(size);
		setCustomerAsPerson(customerAsPerson);
	}
	
	
	
	
}
