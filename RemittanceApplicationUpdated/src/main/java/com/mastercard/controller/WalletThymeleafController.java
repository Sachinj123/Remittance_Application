package com.mastercard.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mastercard.enums.WalletType;
import com.mastercard.model.Wallet;
import com.mastercard.model.WalletReceipt;
import com.mastercard.service.IWalletReceiptService;
import com.mastercard.service.IWalletService;

@Controller
@RequestMapping("/wallet")
public class WalletThymeleafController {

	@Autowired
	private IWalletService walletService;
	
	@Autowired
	private IWalletReceiptService walletReceiptService;
	
	@GetMapping("/listofwallets")
	public String showAllWallets(Model model)
	{
		List<Wallet> wallet = walletService.getAllWalletDetails();
		model.addAttribute("wallet",wallet);
		return "walletdetails";
	}
	
	@GetMapping("/walletReceipts")
	public String showWalletReceipt(Model model)
	{
		List<WalletReceipt> walletReceipts = walletReceiptService.getAllWalletReceipt();
		model.addAttribute("walletReceipts",walletReceipts);
		return "walletReceipts";
	}
	
	@GetMapping("/transfer-money")
	public String showTransferMoney(Model model)
	{
		model.addAttribute("walletTypes",WalletType.values());
		
		model.addAttribute("resultMessage","");
		
		return "transferMoney";
	}

	
	@PostMapping("/transfer-money")
	public String transferMoney(@RequestParam WalletType fromWalletType, @RequestParam String fromRegisterMobileNumber,
			@RequestParam WalletType toWalletType, @RequestParam String toRegisterMobileNumber,
			 @RequestParam BigDecimal amount, Model model)
	{
		String resultMessage = walletService.transferMoney(fromWalletType, toWalletType, fromRegisterMobileNumber, toRegisterMobileNumber, amount);
		
		model.addAttribute("walletTypes",WalletType.values());
		
		model.addAttribute("resultMessage",resultMessage);
		
		return "transferMoney";
	}
	
		@GetMapping("/api/transfer-moneydiff")
		public String showTransferMoneyOfDiffCurrencies(Model model)
		{
			model.addAttribute("walletTypes",WalletType.values());
			
			model.addAttribute("resultMessage","");
			
			return "transferMoneyDiff";
		}
	
	
	  @PostMapping("/api/transfer-moneydiff") 
	  public String transferMoneyOfDiffCurrencies(@RequestParam WalletType fromWalletType, @RequestParam String fromRegisterMobileNumber,
			@RequestParam WalletType toWalletType, @RequestParam String toRegisterMobileNumber,
			 @RequestParam BigDecimal amount,@RequestParam Integer currencyFrom,@RequestParam Integer currencyTo,Model model)
	  {
		  String resultMessage = walletService.transferMoneyOfDiffCurrencies(fromWalletType, toWalletType, amount, fromRegisterMobileNumber, toRegisterMobileNumber, currencyFrom, currencyTo);
		  
		  model.addAttribute("walletTypes",WalletType.values());
		  
		  model.addAttribute("resultMessage",resultMessage);
		  
		  return "transferMoneyDiff";
	  }
	  
		@GetMapping("/transfer-moneydiff")
		public String showTransferMoneyOfDiffCurrency(Model model)
		{
			model.addAttribute("walletTypes",WalletType.values());
			
			model.addAttribute("resultMessage","");
			
			return "transferMoneyDiff";
		}
	 
	
	@GetMapping("/add-funds-from-bank")
	public String showAddFundsFromBankForm(Model model)
	{
		model.addAttribute("walletTypes",WalletType.values());
		
		model.addAttribute("resultMessage","");
		
		return "addFundsFromBank";
	}
	
	
	
	@PostMapping("/add-funds-from-bank")
	public String addFundsFromBank(@RequestParam WalletType walletType, 
			@RequestParam Integer userBankAccounId, @RequestParam BigDecimal amount,Model model)
	{
		String resultMessage = walletService.addFundsFromBank(walletType, userBankAccounId, amount);
		
		model.addAttribute("walletTypes",WalletType.values());
		
		model.addAttribute("resultMessage",resultMessage);
		
		return "addFundsFromBank";
	}
}
