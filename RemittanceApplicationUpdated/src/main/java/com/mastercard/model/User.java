package com.mastercard.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mastercard.security.AES_Encryptor;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_mastercard")
@Getter
@Setter
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int id;
	
	@NotEmpty(message = "Name Can't Be Blank...")
	private String fullName;
	
	@Column(length = 50, unique = true, nullable = false)
	@Email
	private String email;
	
	private LocalDate dateOfBirth;
	
	@NotEmpty(message = "Phone Number Can't Be Blank...")
	@Convert(converter = AES_Encryptor.class)
	private String phoneNumber;
	
	@NotEmpty(message = "Address Can't Be Blank...")
	private String addressLine1;
	
	private String addressLine2;
	
	@NotEmpty(message = "City Can't Be Blank...")
	private String city;
	
	@NotEmpty(message = "Country Can't Be Blank...")
	private String country;

	@Convert(converter = AES_Encryptor.class)
	private String securityQuestion;
	
	@Convert(converter = AES_Encryptor.class)
	private String securityAnswer;
	
	@NotEmpty(message = "Pincode Can't Be Blank...")
	private String pincode;
	
	@NotEmpty(message = "User Role Can't Be Blank...")
	private String userRole;
	
	@NotEmpty(message = "Password Can't Be Blank...")
	private String password;
	
	@OneToMany(mappedBy = "saveuser",cascade = CascadeType.ALL)
	@JsonIgnore
	private List<UserBankAccount> userBankAccount;

	public User(@NotEmpty(message = "Name Can't Be Blank...") String fullName, @Email String email,
			LocalDate dateOfBirth, @NotEmpty(message = "Phone Number Can't Be Blank...") String phoneNumber,
			@NotEmpty(message = "Address Can't Be Blank...") String addressLine1, String addressLine2,
			@NotEmpty(message = "City Can't Be Blank...") String city,
			@NotEmpty(message = "Country Can't Be Blank...") String country, String securityQuestion,
			String securityAnswer, @NotEmpty(message = "Pincode Can't Be Blank...") String pincode,
			@NotEmpty(message = "User Role Can't Be Blank...") String userRole,
			@NotEmpty(message = "Password Can't Be Blank...") String password) {
		super();
		this.fullName = fullName;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
		this.phoneNumber = phoneNumber;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.city = city;
		this.country = country;
		this.securityQuestion = securityQuestion;
		this.securityAnswer = securityAnswer;
		this.pincode = pincode;
		this.userRole = userRole;
		this.password = password;
	}
	
}
