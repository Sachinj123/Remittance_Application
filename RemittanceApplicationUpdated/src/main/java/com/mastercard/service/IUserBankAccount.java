package com.mastercard.service;

import java.util.List;

import com.mastercard.model.UserBankAccount;

public interface IUserBankAccount {

	public List<UserBankAccount> getAllUserBankDetails();
	
	public UserBankAccount saveUserBankAccountDetails(UserBankAccount userBankAccount);
	
	public UserBankAccount updateUserBankAccountDetails(UserBankAccount updateUserBankAccount);
	
	public UserBankAccount findUserBankAccountDetailsById(int id);
	
	public String deleteUserBankAccountDetails(int id);
}
