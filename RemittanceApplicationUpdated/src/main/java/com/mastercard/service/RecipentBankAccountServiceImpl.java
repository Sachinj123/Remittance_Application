package com.mastercard.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mastercard.model.RecipentBankAccount;
import com.mastercard.repository.RecipentBankAccountRepository;
import com.mastercard.resourceNotFound.RecipentBankAccountNotFound;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RecipentBankAccountServiceImpl implements IRecipentBankAccountService{

	@Autowired
	private RecipentBankAccountRepository recipentBankAccountRepository;
	
	@Override
	public RecipentBankAccount saveRecipentBankAccount(RecipentBankAccount recipentBankAccount) {
		return recipentBankAccountRepository.save(recipentBankAccount);
	}

	@Override
	public List<RecipentBankAccount> getAllRecipentBankAccountDetails() {
		return recipentBankAccountRepository.findAll();
	}

	@Override
	public RecipentBankAccount findRecipentBankAccountById(int id) {
		Optional<RecipentBankAccount> optionalRecipentBankAccount  = recipentBankAccountRepository.findById(id);
		return optionalRecipentBankAccount.orElseThrow(()-> new RecipentBankAccountNotFound("Recipent Bank Account Not Found With Id:"+id));
	}

	@Override
	public RecipentBankAccount updateRecipentBankAccount(RecipentBankAccount recipentBankAccount) {
		return recipentBankAccountRepository.save(recipentBankAccount);
	}

	@Override
	public String deleteRecipentBankAccountDetailsById(int id) {
		recipentBankAccountRepository.deleteById(id);
		return "Recipent Bank Account With Id:"+id+" Deleted Successfully...";
	}

	@Override
	public List<RecipentBankAccount> getByRecipientId(int recipentId) {
		return recipentBankAccountRepository.findByRecipent_RecipentId(recipentId);
	}

}
