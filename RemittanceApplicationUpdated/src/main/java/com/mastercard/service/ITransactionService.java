package com.mastercard.service;

import java.math.BigDecimal;

import com.mastercard.model.Receipt;
import com.mastercard.model.TransferRequest;

public interface ITransactionService {

	public Receipt transferMoney(Integer senderAccountId, Integer receiverAccountId,BigDecimal amount);
	
	//public Receipt trasferMoneyFromUserToRecipent(String userBankAccount,String recipentBankAccount,BigDecimal amount);

	//public void transferMoney(String senderAccountNumber,String recipentAccountNumber,BigDecimal amount);

	public Receipt transferMoneyUserToRecipent(String userAccountNumber,String userIFSCCode,String recipentAccountNumber,String recipentIFSCCode,BigDecimal amount);
}
