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

import com.mastercard.model.UserBankAccount;
import com.mastercard.service.IUserBankAccount;

@RestController
@RequestMapping("/userBankAccount")
public class UserBankAccountController {
	
	@Autowired
	private IUserBankAccount iUserBankAccount;
	
	@PostMapping("/save")
	public ResponseEntity<UserBankAccount> createUserBankAccount(@RequestBody UserBankAccount userBankAccount)
	{
		UserBankAccount saveUserBankAccount = iUserBankAccount.saveUserBankAccountDetails(userBankAccount);
		return ResponseEntity.ok().body(saveUserBankAccount);
	}
	
	@GetMapping("/getAll")
	public List<UserBankAccount> getAllUserBankAccountDetails()
	{
		return iUserBankAccount.getAllUserBankDetails();
	}
	
	@GetMapping("/getAccountDetails/{id}")
	public UserBankAccount getUserBankAccountDetailsById(@PathVariable int id)
	{
		return iUserBankAccount.findUserBankAccountDetailsById(id);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteUserBankAccountDetailsById(@PathVariable int id)
	{
		iUserBankAccount.deleteUserBankAccountDetails(id);
	}
	
	@PutMapping("/update")
	public ResponseEntity<UserBankAccount> updateUserBankAccountDetails(@RequestBody UserBankAccount updateUserBankAccount)
	{
		UserBankAccount userBankAccount = iUserBankAccount.updateUserBankAccountDetails(updateUserBankAccount);
		return ResponseEntity.ok().body(userBankAccount);
	}

}
