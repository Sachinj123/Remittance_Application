package com.mastercard.dto;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

	@NotEmpty(message = "Name Can't Be Blank...")
	private String fullName;
	
	@Column(length = 50, unique = true, nullable = false)
	@Email
	private String email;
	
	private LocalDate dateOfBirth;
	
	@NotEmpty(message = "Phone Number Can't Be Blank...")
	private String phoneNumber;
	
	@NotEmpty(message = "Address Can't Be Blank...")
	private String addressLine1;
	
	private String addressLine2;
	
	@NotEmpty(message = "City Can't Be Blank...")
	private String city;
	
	@NotEmpty(message = "Country Can't Be Blank...")
	private String country;

	private String securityQuestion;
	
	private String securityAnswer;
	
	@NotEmpty(message = "Pincode Can't Be Blank...")
	private String pincode;
	
	@NotEmpty(message = "User Role Can't Be Blank...")
	private String userRole;
	
	@NotEmpty(message = "Password Can't Be Blank...")
	private String password;
}
