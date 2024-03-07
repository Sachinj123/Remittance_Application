package com.mastercard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mastercard.model.RecipentBankAccount;
import com.mastercard.service.IRecipentBankAccountService;

@RestController
@RequestMapping("/recipentBankAccount")
public class RecipentBankAccountController {
	
	@Autowired
	private IRecipentBankAccountService recipentBankAccountService;
	
	@PostMapping("/save")
	public ResponseEntity<RecipentBankAccount> createRecipentBankAccount(@RequestBody RecipentBankAccount recipentBankAccount)
	{
		RecipentBankAccount saveRecipentBankAccount = recipentBankAccountService.saveRecipentBankAccount(recipentBankAccount);
		return ResponseEntity.ok().body(saveRecipentBankAccount);
	}
	
	@PutMapping("/update")
	public ResponseEntity<RecipentBankAccount> updateRecipentBankAccount(@RequestBody RecipentBankAccount recipentBankAccount)
	{
		RecipentBankAccount updateRecipentBankAccount = recipentBankAccountService.updateRecipentBankAccount(recipentBankAccount);
		return ResponseEntity.ok().body(updateRecipentBankAccount);
	}
	
	@GetMapping("/getallRecipent_bank_account")
	public List<RecipentBankAccount> getAllRecipentBankAccounts()
	{
		return recipentBankAccountService.getAllRecipentBankAccountDetails();
	}
	
	@GetMapping("/getRecipentBankAccount/{id}")
	public RecipentBankAccount getRecipentBankAccountById(@PathVariable Integer id)
	{
		return recipentBankAccountService.findRecipentBankAccountById(id);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteRecipentBankAccountDetailsById(@PathVariable Integer id)
	{
		recipentBankAccountService.deleteRecipentBankAccountDetailsById(id);
	}
	

}
