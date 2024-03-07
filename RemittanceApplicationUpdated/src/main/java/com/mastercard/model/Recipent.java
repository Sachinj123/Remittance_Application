package com.mastercard.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "recipent")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Recipent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer recipentId;
	
	@NotEmpty(message = "Recipent Name Can't Be Blank...")
	public String recipentName;
	
	@NotEmpty(message = "Recipent Address Can't Be Blank...")
	public String recipentAddress;
	
	public LocalDate recipentDateOfBirth;
	
	@NotEmpty(message = "Recipent Phone Number Can't Be Blank...")
	public String recipentPhoneNumber;
	
	@OneToMany(mappedBy = "recipent",cascade = CascadeType.ALL)
	@JsonIgnore
	private List<RecipentBankAccount> recipentBankAccounts;
}
