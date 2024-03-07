package com.mastercard.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mastercard.model.Receipt;
import com.mastercard.model.TransferRequest;
import com.mastercard.service.ITransactionService;

@RestController
@RequestMapping("/banking")
public class BankTransferController {	
	
	@Autowired
	private ITransactionService iTransactionService;
	
	@PostMapping("/transfer")
	public ResponseEntity<Receipt> transferMoney(@RequestParam Integer senderId,@RequestParam Integer receiverId, @RequestParam BigDecimal amount)
	{
		Receipt receipt = iTransactionService.transferMoney(senderId, receiverId, amount);
		return new ResponseEntity<>(receipt,HttpStatus.OK);
	}
	
	/*
	 * @PostMapping("/transferUserToRecipent") public ResponseEntity<Receipt>
	 * transferMoneyUserToRecipent(@RequestParam String
	 * userBankAccount, @RequestParam String recipentBankAccount, @RequestParam
	 * BigDecimal amount) { Receipt receipt =
	 * iTransactionService.trasferMoneyFromUserToRecipent(userBankAccount,
	 * recipentBankAccount, amount); return new
	 * ResponseEntity<>(receipt,HttpStatus.OK); }
	 */
	
	/*
	 * @PostMapping("/transferUserToRecipent") public ResponseEntity<Receipt>
	 * transferMoneyUserToRecipent(@RequestBody TransferRequest transferRequest) {
	 * Receipt receipt =
	 * iTransactionService.transferMoneyFromUserToRecipent(transferRequest); return
	 * new ResponseEntity<>(receipt,HttpStatus.OK); }
	 */
	
//	@PostMapping("/transferUserToRecipent")
//	public ResponseEntity<String> transferMoney(@RequestBody TransferRequest transferRequest)
//	{
//		iTransactionService.transferMoney(transferRequest.getSenderBankAccount(), 
//				transferRequest.getReceiverBankAccount(), transferRequest.getAmountTransfer());
//	
//		return ResponseEntity.ok("Money Transferred Successfully...");
//	}
	
	@PostMapping("/funds-transfer")
	public ResponseEntity<String> transferMoneyFromUserToRecipent(@RequestBody TransferRequest transferRequest)
	{
		iTransactionService.transferMoneyUserToRecipent(transferRequest.getSenderBankAccount(), 
				transferRequest.getUserIFSCCode(), transferRequest.getReceiverBankAccount(), 
				transferRequest.getRecipentIFSCCode(),transferRequest.getAmountTransfer());
		
		return ResponseEntity.ok("Money Transferred Successfully...");
	}
}
