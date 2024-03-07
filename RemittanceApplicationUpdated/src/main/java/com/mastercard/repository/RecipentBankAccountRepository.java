package com.mastercard.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mastercard.model.RecipentBankAccount;

public interface RecipentBankAccountRepository extends JpaRepository<RecipentBankAccount, Integer>{

	public Optional<RecipentBankAccount> findByRecipentBankAccountNumber(String recipentBankAccountNumber);
	
	public Optional<RecipentBankAccount> 
		findByRecipentBankAccountNumberAndRecipentBankIFSCCode
				(String recipentBankAccountNumber,String recipentIfscCode);
	
	public List<RecipentBankAccount> findByRecipent_RecipentId(int id);
}
