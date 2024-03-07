package com.mastercard.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "recipentbankaccount")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecipentBankAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer recipentBankAccountId;
	
	@NotEmpty(message = "Recipent Bank Name Can't Be Blank...")
	private String recipentBankName;
	
	@NotEmpty(message = "Recipent Bank Account Number Can't Be Blank...")
	private String recipentBankAccountNumber;
	
	@NotEmpty(message = "Recipent Bank IFSC Code Can't Be Blank...")
	private String recipentBankIFSCCode;
	
	private BigDecimal recipentAccountBalance;
	
	@ManyToOne
	@JoinColumn(name = "recipent_id")
	private Recipent recipent;
}
