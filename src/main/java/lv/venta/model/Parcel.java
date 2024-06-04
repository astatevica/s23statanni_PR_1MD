package lv.venta.model;

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
import java.time.LocalDateTime;

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
	
	@Column(name = "Order_created")
	@Setter
	private LocalDateTime order_created;
	
	@Column(name = "Order_delivery")
	@Setter
	private LocalDateTime order_delivery;
	
	@Column(name = "Price")
	@NotNull
	@Setter
	@Min((long)0.1)
	private float price;
	
	@Column(name = "Size")
	@NotNull
	private Size size;
	
	//saite no AbstractCustomer
	@OneToOne
	@JoinColumn(name = "Idac")
	private AbstractCustomer abstractCustomer;
	
	//saite no Driver
	@OneToOne
	@JoinColumn(name = "Idd")
	private Driver driver;
	
	
	public Parcel(boolean is_fragile, int days, float price, Size size, AbstractCustomer abstractCustomer, Driver driver) {
		set_fragile(is_fragile);
		setOrder_created(LocalDateTime.now());
		setOrder_delivery(LocalDateTime.now().plusDays(days));
		setPrice(price);
		setSize(size);
		setAbstractCustomer(abstractCustomer);
		setDriver(driver);
	}
	
	
	
	
}
