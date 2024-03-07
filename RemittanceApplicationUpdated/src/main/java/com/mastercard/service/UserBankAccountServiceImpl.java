package com.mastercard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mastercard.model.UserBankAccount;
import com.mastercard.repository.UserBankAccountRepository;
import com.mastercard.resourceNotFound.UserBankDetailsNotFound;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserBankAccountServiceImpl implements IUserBankAccount{

	@Autowired
	private UserBankAccountRepository userBankAccountRepository;
	
	@Override
	public List<UserBankAccount> getAllUserBankDetails() {
		return userBankAccountRepository.findAll();
	}

	@Override
	public UserBankAccount saveUserBankAccountDetails(UserBankAccount userBankAccount) {
		return userBankAccountRepository.save(userBankAccount);
	}

	@Override
	public UserBankAccount updateUserBankAccountDetails(UserBankAccount updateUserBankAccount) {
		return userBankAccountRepository.save(updateUserBankAccount);
	}

	@Override
	public UserBankAccount findUserBankAccountDetailsById(int id) {
		UserBankAccount userBankAccount = userBankAccountRepository.findById(id)
				.orElseThrow(()->new UserBankDetailsNotFound("Bank Details of User With Id:"+id+" not Found...!!"));
		return userBankAccount;
	}

	@Override
	public String deleteUserBankAccountDetails(int id) {
		 userBankAccountRepository.deleteById(id);
		return "Bank Details of User With Id:"+id+" Deleted Successfully...!!";
	}

}
