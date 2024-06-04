package lv.venta.model;

import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "AbstractCustomerTable")
@Entity
public class AbstractCustomer {
	@Id
	@Column(name = "Idac")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	private int idac;
	
	@Column(name = "Phone_no")
	@NotNull
	@Size(min = 8, max = 8)
	@Pattern(regexp = "[0-9]{8}", message = "Only numbers are allowed with out country code before")
	private String phone_no;
	
	//saite uz Address
	@ManyToOne
	@JoinColumn(name="Ida")
	private Address address;
	
	//saite no CustomerAsCompany
	@OneToOne
	@JoinColumn(name="Idcc")
	private CustomerAsCompany customerAsCompany;
	
	//saite no CustomerAsPerson
	@OneToOne
	@JoinColumn(name="Idcp")
	private CustomerAsPerson customerAsPerson;
	
	//saite uz Parcel
	@OneToMany(mappedBy = "abstractCustomer")
	@ToString.Exclude
	private Collection<Parcel> parcel;
	
	public AbstractCustomer(String phone_no, Address address, CustomerAsCompany customerAsCompany, CustomerAsPerson customerAsPerson) {
		setPhone_no(phone_no);
		setAddress(address);
		setCustomerAsCompany(customerAsCompany);
		setCustomerAsPerson(customerAsPerson);
	}
}
