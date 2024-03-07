package com.mastercard.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "receipts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Receipt {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer receiptId;
	
	@Column(nullable = false)
	private String senderName;
	
	@Column(nullable = false)
	private String receiverName;
	
	@Column(nullable = false)
	private String senderBankAccount;
	
	@Column(nullable = false)
	private String receiverBankAccount;
	
	@Column(nullable = false)
	private String userIFSCCode;
	
	@Column(nullable = false)
	private String recipentIFSCCode;
	
	@Column(nullable = false)
	private LocalDateTime dateAndTime;
	
	@Column(nullable = false)
	private BigDecimal amountTransfer;
}
