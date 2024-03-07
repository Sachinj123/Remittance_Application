package com.mastercard.model;

import java.math.BigDecimal;

import com.mastercard.security.AES_Encryptor;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="userBankAccount")
@Getter
@Setter
@NoArgsConstructor
public class UserBankAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userBankAccountId;
	
	@NotEmpty(message = "Bank Name Can't Be Blank....")
	private String bankName;
	
	@NotEmpty(message = "Account Number Can't Be Blank....")
	@Convert(converter = AES_Encryptor.class)
	private String accountNumber;
	
	private String IFSCCode;
	
	private BigDecimal accountBalance;
	
	@NotEmpty(message = "Bank Address Can't Be Blank....")
	private String bankAddress;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User saveuser;
}
