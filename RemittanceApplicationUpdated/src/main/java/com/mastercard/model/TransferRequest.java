package com.mastercard.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransferRequest {

	private String senderBankAccount;
	
	private String receiverBankAccount;
	
	private BigDecimal amountTransfer;
	
	private String userIFSCCode;
	
	private String recipentIFSCCode;
	
}
