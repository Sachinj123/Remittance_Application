package com.mastercard.service;

import java.util.List;

import com.mastercard.model.RecipentBankAccount;

public interface IRecipentBankAccountService {

	public RecipentBankAccount saveRecipentBankAccount(RecipentBankAccount recipentBankAccount);
	
	public List<RecipentBankAccount> getAllRecipentBankAccountDetails();
	
	public RecipentBankAccount findRecipentBankAccountById(int id);
	
	public RecipentBankAccount updateRecipentBankAccount(RecipentBankAccount recipentBankAccount);
	
	public String deleteRecipentBankAccountDetailsById(int id);
	
	public List<RecipentBankAccount> getByRecipientId(int recipentId);
}
