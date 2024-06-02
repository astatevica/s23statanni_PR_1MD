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
@Table(name = "CustomerAsCompanyTable")
@Entity
public class CustomerAsCompany {
	@Id
	@Column(name = "Idc")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	private int idc;
	
	@Column(name = "Company_reg_no")
	@NotNull
	@Size(min = 13, max = 13)
	@Pattern(regexp = "[L]{1}[V]{1}[0-9]{11}", message = "Invalid registration number")
	private String company_reg_no;
	 
	@Column(name = "Customer_code")
	@NotNull
	@Setter
	private String customer_code;
	
	@Column(name = "Phone_no")
	@NotNull
	@Size(min = 8, max = 8)
	@Pattern(regexp = "[0-9]{8}", message = "Only numbers are allowed with out country code before")
	private String phone_no;
	
	@Column(name = "Title")
	@NotNull
	@Size(min = 5, max = 50)
	private String title;
	
	//TODO te nāks saite uz Address
	
	public CustomerAsCompany(String customer_code, String phone_no, String title) {
		setCompany_reg_no(company_reg_no="0_company_"+customer_code); //TODO pārbaudīt vai strādā
		setCustomer_code(customer_code);
		setPhone_no(phone_no);
		setTitle(title);
	}
	
	

}
