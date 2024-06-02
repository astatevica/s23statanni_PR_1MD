package lv.venta.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
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
@Table(name = "DriverTable")
@Entity
public class Driver {

	@Id
	@Column(name = "Idd")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	private int idd;
	
	//TODO ja paliek pāri laika, tad izveidot ar mantošanu
	@Column(name = "Name")
	@NotNull
	@Size(min = 3, max = 50)
	@Pattern(regexp = "[A-ZĒŪĪĻĶĢŠĀŽČŅ]{1}[a-zēūīļķģšāžčņ]+", message = "Only letters and space are allowed")
	private String name;
	
	//TODO ja paliek pāri laika, tad izveidot ar mantošanu
	@Column(name = "Person_code")
	@NotNull
	@Size(min = 12, max = 12)
	@Pattern(regexp = "[0-9]{6}-[0-9]{5}", message = "Only letters and space are allowed")
	private String person_code;
	
	//TODO ja paliek pāri laika, tad izveidot ar mantošanu
	@Column(name = "Surname")
	@NotNull
	@Size(min = 3, max = 50)
	@Pattern(regexp = "[A-ZĒŪĪĻĶĢŠĀŽČŅ]{1}[a-zēūīļķģšāžčņ]+", message = "Only letters and space are allowed")
	private String surname;
	
	@Column(name = "Experience_in_years")
	@Min(1)
	private float experience_in_years;
	
	@Column(name = "License_no")
	@NotNull
	@Size(min = 8, max = 8)
	@Pattern(regexp = "[A]{1}[T]{1}[0-9]{6}", message = "Invalid license no")
	private String license_no;
	
	//saite uz Parcel
	@OneToOne(mappedBy = "driver")
	@ToString.Exclude
	private Parcel parcel;
	
	public Driver(String name,String person_code, String surname, float experience_in_years, String license_no) {
		setName(surname);
		setPerson_code(person_code);
		setSurname(surname);
		setExperience_in_years(experience_in_years);
		setLicense_no(license_no);
	}
	
	
}
