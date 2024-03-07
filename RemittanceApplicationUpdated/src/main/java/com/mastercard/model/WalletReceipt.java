package com.mastercard.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.mastercard.enums.WalletType;

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
@Table(name = "walletReceipt")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WalletReceipt {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer walletReceiptId;
	
	@Column(nullable = false)
	private WalletType fromWalletType;
	
	@Column(nullable = false)
	private WalletType toWalletType;
	
	@Column(nullable = false)
	private String senderMobileNumber;
	
	@Column(nullable = false)
	private String receiverMobileNumber;
	
	@Column(nullable = false)
	private LocalDateTime dateAndTime;
	
	@Column(nullable = false)
	private BigDecimal amountTransfer;
	
}
