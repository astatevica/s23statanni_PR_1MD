package lv.venta.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
@Table(name = "CustomerAsPersonTable")
@Entity
public class CustomerAsPerson {

	@Id
	@Column(name = "Idcp")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	private int idcp;
	
	@Column(name = "Customer_code")
	@Setter
	private String customer_code;
	
	@Column(name = "Person_code")
	@NotNull
	@Size(min = 12, max = 12)
	@Pattern(regexp = "[0-9]{6}-[0-9]{5}", message = "Only numbers and '-' are allowed")
	private String person_code;
	
	@Column(name = "Phone_no")
	@NotNull
	@Size(min = 8, max = 8)
	@Pattern(regexp = "[0-9]{8}", message = "Only numbers are allowed with out country code before")
	private String phone_no;
	
	//saite uz Address
	@OneToOne
	@JoinColumn(name="Ida")
	private Address address;
	
	//saite no Person
	@OneToOne
	@JoinColumn(name = "Idp")
	private Person person;
	
	//saite uz Parcel
	@OneToOne(mappedBy = "customerAsPerson")
	@ToString.Exclude
	private Parcel parcel;
	
	public CustomerAsPerson(String person_code, String phone_no, Person person, Address address) {
		setCustomer_code(customer_code="0_person_"+person_code); //TODO pārbaudīt vai vispār strādā
		setPerson_code(person_code);
		setPhone_no(phone_no);
		setPerson(person);
		setAddress(address);
	}
	
	
}
