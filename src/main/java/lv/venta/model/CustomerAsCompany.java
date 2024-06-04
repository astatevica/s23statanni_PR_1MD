package lv.venta.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "CustomerAsCompanyTable")
@Entity
public class CustomerAsCompany {
	@Id
	@Column(name = "Idcc")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	private int idcc;
	
	@Column(name = "Company_reg_no")
	@NotNull
	@Size(min = 13, max = 13)
	@Pattern(regexp = "[L]{1}[V]{1}[0-9]{11}", message = "Invalid registration number")
	@Setter
	private String company_reg_no;
	 
	@Column(name = "Customer_code")
	@Setter
	private String customer_code;
	
	@Column(name = "Title")
	@NotNull
	@Size(min = 5, max = 50)
	@Setter
	private String title;
	
	//saite uz AbstractCustomer
	@OneToOne(mappedBy = "customerAsCompany")
	@ToString.Exclude
	private AbstractCustomer abstractCustomer;
	
	public CustomerAsCompany(String company_reg_no, String title) {
		setCompany_reg_no(company_reg_no);
		setCustomer_code(company_reg_no="0_company_"+company_reg_no);
		setTitle(title);
	}
	
	

}
