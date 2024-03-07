package com.mastercard.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mastercard.enums.WalletType;
import com.mastercard.model.AddFundsRequest;
import com.mastercard.model.Wallet;
import com.mastercard.model.WalletReceipt;
import com.mastercard.model.WalletTransferRequest;
import com.mastercard.service.IWalletService;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {

	@Autowired
	private IWalletService walletService;
	
	@PostMapping("/transfer")
	public ResponseEntity<String> transferMoney(@RequestParam WalletType fromWalletType, @RequestParam WalletType toWalletType,@RequestParam BigDecimal amount,
			@RequestParam String fromRegisterMobileNumber,@RequestParam String toRegisterMobileNumber,@RequestParam Integer currencyFrom,@RequestParam Integer currencyTo)
	{
		 walletService.transferMoneyOfDiffCurrencies(fromWalletType, toWalletType, amount, fromRegisterMobileNumber, toRegisterMobileNumber, currencyFrom, currencyTo);	
		 
		 return ResponseEntity.ok("Money Transferred Successfully...");
	}
	
	@GetMapping("/{walletType}")
	public Wallet getWallet(@PathVariable WalletType walletType)
	{
		return walletService.getWalletByType(walletType);
	}
	
	/*
	 * @PostMapping("/transfer-money") public void transferMoney(@RequestBody
	 * WalletTransferRequest walletTransferRequest) {
	 * walletService.transferMoney(walletTransferRequest.getFromWalletType(),
	 * walletTransferRequest.getToWalletType(), walletTransferRequest.getAmount());
	 * }
	 */
	
	@PostMapping("/add-funds-from-bank/{walletType}")
	public void addFundsFromBank(@PathVariable WalletType walletType,
			@RequestBody AddFundsRequest addFundsRequest)
	{
		walletService.addFundsFromBank(walletType, 
				addFundsRequest.getUserBankAccountId(), addFundsRequest.getAmount());
	}
	
	@GetMapping("/get-all")
	public List<Wallet> getAllWalletsDetails()
	{
		return walletService.getAllWalletDetails();
	}
	
	@PostMapping("/save")
	public ResponseEntity<Wallet> saveWalletDetails(@RequestBody Wallet wallet)
	{
		Wallet saveWallet = walletService.saveWallet(wallet);
		return ResponseEntity.ok().body(saveWallet);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Wallet> updateWalletDetails(@RequestBody Wallet wallet)
	{
		Wallet updateWallet = walletService.updateWalletDetails(wallet);
		return ResponseEntity.ok().body(updateWallet);
	}
}
