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
	
	
	@Column(name = "CustomerCode")
	@Setter
	private String customerCode;
	
	@Column(name = "Person_code")
	@NotNull
	@Size(min = 12, max = 12)
	@Pattern(regexp = "[0-9]{6}-[0-9]{5}", message = "Only numbers and '-' are allowed")
	private String person_code;
	
	//saite no Person
	@OneToOne
	@JoinColumn(name = "Idp")
	private Person person;
	
	//saite uz AbstractCustomer
	@OneToOne(mappedBy = "customerAsPerson")
	@ToString.Exclude
	private AbstractCustomer abstractCustomer;
	
	public CustomerAsPerson(String person_code, Person person) {
		setCustomerCode(customerCode="0_person_"+person_code);
		setPerson_code(person_code);
		setPerson(person);
	}
	
	
}
