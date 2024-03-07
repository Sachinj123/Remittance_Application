package com.mastercard.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mastercard.model.AmountTransferConfigurationPage;
import com.mastercard.model.Receipt;
import com.mastercard.model.RecipentBankAccount;
import com.mastercard.model.TransferRequest;
import com.mastercard.model.UserBankAccount;
import com.mastercard.repository.ReceiptRepository;
import com.mastercard.repository.RecipentBankAccountRepository;
import com.mastercard.repository.UserBankAccountRepository;
import com.mastercard.resourceNotFound.InsufficientFundException;
import com.mastercard.resourceNotFound.InvalidAccountException;
import com.mastercard.resourceNotFound.RecipentBankAccountNotFound;
import com.mastercard.resourceNotFound.UserBankDetailsNotFound;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class TransactionServiceImpl implements ITransactionService{
	
	@Autowired
	private ReceiptRepository receiptRepository;
	
	@Autowired
	private UserBankAccountRepository userBankAccountRepository;
	
	@Autowired
	private RecipentBankAccountRepository recipentBankAccountRepository;
	
	@Autowired
	private IAmountTransferConfigurationPageService amountTransferConfigurationPageService;

	@Override
	public Receipt transferMoney(Integer senderAccountId, Integer receiverAccountId, BigDecimal amount) {
		
		UserBankAccount senderBankAccount = userBankAccountRepository.findById(senderAccountId)
				.orElseThrow(()-> new UserBankDetailsNotFound("Sender Bank Account is Not Found..."));
		
		UserBankAccount receiverBankAccount = userBankAccountRepository.findById(receiverAccountId)
				.orElseThrow(()-> new UserBankDetailsNotFound("Receiver Bank Account is Not Found..."));
		
		debit(senderBankAccount, amount);
		credit(receiverBankAccount, amount);
		
		Receipt receipt = new Receipt();
		
		receipt.setSenderName(senderBankAccount.getSaveuser().getFullName());
		
		receipt.setReceiverName(receiverBankAccount.getSaveuser().getFullName());
		
		receipt.setSenderBankAccount(senderBankAccount.getAccountNumber());
		
		receipt.setReceiverBankAccount(receiverBankAccount.getAccountNumber());
		
		receipt.setDateAndTime(LocalDateTime.now());
		
		receipt.setAmountTransfer(amount);
		
		receiptRepository.save(receipt);
		
		return receipt;
	}
	
	//BigDecimal.ZERO - The value 0, with a scale of 0.
	private void debit(UserBankAccount account, BigDecimal amount)
	{
		BigDecimal newBalance = account.getAccountBalance().subtract(amount);
		
		if(newBalance.compareTo(BigDecimal.ZERO) < 0)
		{
			throw new InsufficientFundException("Insufficient Funds In the Sender Account...");
		}
		
		account.setAccountBalance(newBalance);
		
		userBankAccountRepository.save(account);
	}
	
	private void credit(UserBankAccount account,BigDecimal amount)
	{
		account.setAccountBalance(account.getAccountBalance().add(amount));
		
		userBankAccountRepository.save(account);
	}

	@Override
	public Receipt transferMoneyUserToRecipent(String userAccountNumber, String userIFSCCode, 
			String recipentAccountNumber,String recipentIFSCCode, BigDecimal amount) {
		
		UserBankAccount userBankAccount = userBankAccountRepository.findByAccountNumberAndIFSCCode(userAccountNumber, userIFSCCode)
						.orElseThrow(()-> new UserBankDetailsNotFound("User Bank Details Are Not Found with:"+userAccountNumber+" or with IFSC Code::"+userIFSCCode));
		
		RecipentBankAccount recipentBankAccount = recipentBankAccountRepository.findByRecipentBankAccountNumberAndRecipentBankIFSCCode(recipentAccountNumber, recipentIFSCCode)
						.orElseThrow(()-> new RecipentBankAccountNotFound("Recipent Bank Account Details Are Not Found With:"+recipentAccountNumber+" or with IFSC Code::"+recipentIFSCCode));
		
		AmountTransferConfigurationPage configuration = amountTransferConfigurationPageService.getLatestConfiguration();
		
		if(amount.compareTo(configuration.getMinAmountTransfer())< 0 
				|| amount.compareTo(configuration.getMaxAmountTransfer())> 0) {
			throw new IllegalArgumentException("Transfer Amount should be More than 100 and Less than 1000");
		}
		if(userBankAccount.getAccountBalance().compareTo(amount) > 0)
		{
			userBankAccount.setAccountBalance(userBankAccount.getAccountBalance().subtract(amount));
			
			recipentBankAccount.setRecipentAccountBalance(recipentBankAccount.getRecipentAccountBalance().add(amount));
			
			userBankAccountRepository.save(userBankAccount);
			
			recipentBankAccountRepository.save(recipentBankAccount);
			
			Receipt receipt = new Receipt();
			
			receipt.setSenderName(userBankAccount.getSaveuser().getFullName());
			
			receipt.setReceiverName(recipentBankAccount.getRecipent().getRecipentName());
			
			receipt.setSenderBankAccount(userBankAccount.getAccountNumber());
			
			receipt.setReceiverBankAccount(recipentBankAccount.getRecipentBankAccountNumber());
			
			receipt.setAmountTransfer(amount);
			
			receipt.setUserIFSCCode(userBankAccount.getIFSCCode());
			
			receipt.setRecipentIFSCCode(recipentBankAccount.getRecipentBankIFSCCode());
			
			receipt.setDateAndTime(LocalDateTime.now());
			
			receiptRepository.save(receipt);
			
			return receipt;
		}
		else
		{
			throw new InsufficientFundException("Insufficient Funds In The Sender Account");
		}
			
}
	

//	@Override
//	public void transferMoney(String senderAccountNumber, String recipentAccountNumber, BigDecimal amount) {
//		
//		UserBankAccount senderBankAccount = userBankAccountRepository.findByAccountNumber(senderAccountNumber)
//				.orElseThrow(()-> new UserBankDetailsNotFound("User Bank Details Are Not Found with:"+senderAccountNumber));
//		
//		RecipentBankAccount recipentBankAccount = recipentBankAccountRepository.findByRecipentBankAccountNumber(recipentAccountNumber)
//				.orElseThrow(()-> new RecipentBankAccountNotFound("Recipent Bank Account Details Are Not Found With:"+recipentAccountNumber));
//	
//	
//		if(senderBankAccount.getAccountBalance().compareTo(amount) >= 0)
//		{
//			senderBankAccount.setAccountBalance(senderBankAccount.getAccountBalance().subtract(amount));
//		
//			recipentBankAccount.setRecipentAccountBalance(recipentBankAccount.getRecipentAccountBalance().add(amount));
//		
//			userBankAccountRepository.save(senderBankAccount);
//			
//			recipentBankAccountRepository.save(recipentBankAccount);
//			
//			Receipt receipt = new Receipt();
//			
//			receipt.setSenderName(senderBankAccount.getSaveuser().getFullName());
//			
//			receipt.setReceiverName(recipentBankAccount.getRecipent().getRecipentName());
//			
//			receipt.setSenderBankAccount(senderBankAccount.getAccountNumber());
//			
//			receipt.setReceiverBankAccount(recipentBankAccount.getRecipentBankAccountNumber());
//			
//			receipt.setDateAndTime(LocalDateTime.now());
//			
//			receipt.setAmountTransfer(amount);
//			
//			receiptRepository.save(receipt);
//		}
//		
//		else
//		{
//			throw new RuntimeException("Insufficient Funds In The Sender Account");
//		}
//	
//	}
	
	
	

	/*
	 * @Override public Receipt trasferMoneyFromUserToRecipent(String
	 * userBankAccount, String recipentBankAccount, BigDecimal amount) {
	 * 
	 * UserBankAccount senderBankAccount =
	 * userBankAccountRepository.findByAccountNumber(userBankAccount);
	 * 
	 * RecipentBankAccount receiverBankAccount =
	 * recipentBankAccountRepository.findByRecipentBankAccountNumber(
	 * recipentBankAccount);
	 * 
	 * debitFromUserBankAccount(senderBankAccount, amount);
	 * creditIntoRecipentBankAccount(receiverBankAccount, amount);
	 * 
	 * Receipt receipt = new Receipt();
	 * 
	 * receipt.setSenderName(senderBankAccount.getSaveuser().getFullName());
	 * 
	 * receipt.setReceiverName(receiverBankAccount.getRecipent().getRecipentName());
	 * 
	 * receipt.setSenderBankAccount(senderBankAccount.getAccountNumber());
	 * 
	 * receipt.setReceiverBankAccount(receiverBankAccount.
	 * getRecipentBankAccountNumber());
	 * 
	 * receipt.setDateAndTime(LocalDateTime.now());
	 * 
	 * receipt.setAmountTransfer(amount);
	 * 
	 * receiptRepository.save(receipt);
	 * 
	 * return receipt; }
	 */
	

}
