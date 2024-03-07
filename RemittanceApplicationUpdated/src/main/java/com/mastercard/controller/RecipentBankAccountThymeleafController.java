package com.mastercard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mastercard.model.RecipentBankAccount;
import com.mastercard.service.IRecipentBankAccountService;

@Controller
@RequestMapping("/recipientBankAccounts")
public class RecipentBankAccountThymeleafController {

	@Autowired
	private IRecipentBankAccountService recipentBankAccountService;
	
	@GetMapping("/details")
	public String showRecipientBankAccountDetails(@RequestParam int recipientId,Model model)
	{
		List<RecipentBankAccount> recipintBankAccounts = recipentBankAccountService.getByRecipientId(recipientId);
		
		model.addAttribute("recipintBankAccounts",recipintBankAccounts);
		
		return "recipientBankAccountDetails::recipientBankAccountDetailsFragment";
	}
	
}
