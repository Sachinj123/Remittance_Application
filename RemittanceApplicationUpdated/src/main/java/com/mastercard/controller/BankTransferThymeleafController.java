package com.mastercard.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mastercard.model.Receipt;
import com.mastercard.service.ITransactionService;

@Controller
@RequestMapping("/transaction")
public class BankTransferThymeleafController {

	@Autowired
	private ITransactionService transactionService;
	
	@GetMapping("/transfer")
	public String showTransferMoneyPage()
	{
		return "banktransferMoney";
	}
	
	@PostMapping("/transfer")
	public String transferMoney(@RequestParam("userAccountNumber") String userAccountNumber,
			@RequestParam("userIFSCCode")String userIFSCCode,
			@RequestParam("recipentAccountNumber") String recipentAccountNumber,
			@RequestParam("recipentIFSCCode") String recipentIFSCCode,
			@RequestParam("amount") BigDecimal amount,Model model)
	{
		try 
		{
			Receipt receipt = transactionService.transferMoneyUserToRecipent(userAccountNumber, userIFSCCode, recipentAccountNumber, recipentIFSCCode, amount);
			
			model.addAttribute("receipt",receipt);
			
			return "transactionSuccess";
		} 
		
		catch (IllegalArgumentException e) {
				model.addAttribute("error",e.getMessage());
				return "banktransferMoney";
		}
		
		
	}
}
