package com.mastercard.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mastercard.model.UserBankAccount;

public interface UserBankAccountRepository extends JpaRepository<UserBankAccount, Integer>{

	public Optional<UserBankAccount> findByAccountNumber(String accountNumber);
	
	public Optional<UserBankAccount> findByAccountNumberAndIFSCCode(String accountNumber,String ifscCode);
}
