package lv.venta.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	@Column(name = "Idc")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	private int idc;
	
	@Column(name = "Customer_code")
	@NotNull
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
	
	//TODO saite no adreses būs šeit
	//private Address address;
	
	//TODO saite no persones šeit būs
	//private Person person;
	
	public CustomerAsPerson(String person_code, String phone_no) {
		setCustomer_code(customer_code="0_person_"+person_code); //TODO pārbaudīt vai vispār strādā
		setPerson_code(person_code);
		setPhone_no(phone_no);
	}
	
	
}
