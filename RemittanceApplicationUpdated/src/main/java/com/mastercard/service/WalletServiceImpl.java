package com.mastercard.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mastercard.enums.WalletType;
import com.mastercard.model.AmountTransferConfigurationPage;
import com.mastercard.model.UserBankAccount;
import com.mastercard.model.Wallet;
import com.mastercard.model.WalletReceipt;
import com.mastercard.repository.UserBankAccountRepository;
import com.mastercard.repository.WalletReceiptRepository;
import com.mastercard.repository.WalletRepository;
import com.mastercard.resourceNotFound.InsufficientFundException;
import com.mastercard.resourceNotFound.UserBankDetailsNotFound;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class WalletServiceImpl implements IWalletService{

	@Autowired
	private WalletRepository walletRepository;
	
	@Autowired
	private UserBankAccountRepository userBankAccountRepository;
	
	@Autowired
	private WalletReceiptRepository walletReceiptRepository;
	
	@Autowired
	private ICurrencyConversionService currencyConversionService;
	
	private AmountTransferConfigurationPage atcp = new AmountTransferConfigurationPage();
	
	@Override
	public Wallet getWalletByType(WalletType walletType) {
		return walletRepository.findByWalletType(walletType);
	}

	@Override
	public void updateBalance(WalletType walletType, BigDecimal amount) {
		
		Wallet wallet = walletRepository.findByWalletType(walletType);
		
		wallet.setBalance(wallet.getBalance().add(amount));
		
		walletRepository.save(wallet);
	}

	@Override
	public String transferMoney(WalletType fromWalletType, WalletType toWalletType,
			String fromRegisterMobileNumber,String toRegisterMobileNumber, BigDecimal amount) {
		
		Wallet fromWallet = walletRepository.findByWalletTypeAndRegisteredMobileNumber(fromWalletType, fromRegisterMobileNumber);
		
		Wallet toWallet = walletRepository.findByWalletTypeAndRegisteredMobileNumber(toWalletType, toRegisterMobileNumber);
		
		if(fromWallet == null || toWallet == null)
		{
			return "Error: Invalid Wallet Types or Mobile Number...";
		}
		
		if(amount.compareTo(BigDecimal.valueOf(100)) > 0 && amount.compareTo(BigDecimal.valueOf(1000)) < 0)
		{
		if(fromWallet.getBalance().compareTo(amount) >= 0)
		{
			fromWallet.setBalance(fromWallet.getBalance().subtract(amount));
			
			toWallet.setBalance(toWallet.getBalance().add(amount));
			
			walletRepository.save(fromWallet);
			
			walletRepository.save(toWallet);
			
			WalletReceipt walletReceipt = new WalletReceipt();
			
			walletReceipt.setFromWalletType(fromWalletType);
			
			walletReceipt.setToWalletType(toWalletType);
			
			walletReceipt.setSenderMobileNumber(fromRegisterMobileNumber);
			
			walletReceipt.setReceiverMobileNumber(toRegisterMobileNumber);
			
			walletReceipt.setDateAndTime(LocalDateTime.now());
			
			walletReceipt.setAmountTransfer(amount);
			
			
			walletReceiptRepository.save(walletReceipt);
			
			return "Success: Money Transferred Succesfully...";
		}
		else
		{
			throw new InsufficientFundException("Insufficient Balance in:"+fromWalletType+" wallet");
		}
	}
		else
		{
		return "Transfer Amount Must Be Greater Than 100 and less than 1000";
	}
}		

	@Override
	public String addFundsFromBank(WalletType walletType, Integer userBankAccountId, BigDecimal amount) {
		
		Wallet wallet = walletRepository.findByWalletType(walletType);
		
		UserBankAccount userBankAccount = userBankAccountRepository.findById(userBankAccountId)
				.orElseThrow(() -> new UserBankDetailsNotFound("User Bank Account Not Found..."));
		
		if(wallet == null || userBankAccount == null)
		{
			return "Error: Invalid Wallet Type or Bank Account...";
		}
		
		if(amount.compareTo(BigDecimal.valueOf(100)) > 0 && amount.compareTo(BigDecimal.valueOf(1000)) < 0)
		{
		
		if(userBankAccount.getAccountBalance().compareTo(amount) >=0)
		{
			userBankAccount.setAccountBalance(userBankAccount.getAccountBalance().subtract(amount));
			
			wallet.setBalance(wallet.getBalance().add(amount));
			
			userBankAccountRepository.save(userBankAccount);
			
			walletRepository.save(wallet);
			
			return "Success: Funds Added to Wallet Successfully...";
		}
		else
		{
			return "Error Insufficient Funds in the Bank Account";
		}
		
	  }
		else
		{
			return "Transfer Amount Must Be Greater Than 100 and less than 1000";
		}
}

	@Override
	public Wallet saveWallet(Wallet wallet) {
		return walletRepository.save(wallet);
	}

	@Override
	public List<Wallet> getAllWalletDetails() {
		return walletRepository.findAll();
	}

	@Override
	public Wallet updateWalletDetails(Wallet wallet) {
		return walletRepository.save(wallet);
	}

	@Override
	public String transferMoneyOfDiffCurrencies(WalletType fromWalletType, WalletType toWalletType, BigDecimal amount,
			String fromRegisterMobileNumber, String toRegisterMobileNumber, Integer currencyFrom, Integer currencyTo) {
		
		Wallet fromWallet = walletRepository.findByWalletTypeAndRegisteredMobileNumber(fromWalletType, fromRegisterMobileNumber);
		
		Wallet toWallet = walletRepository.findByWalletTypeAndRegisteredMobileNumber(toWalletType, toRegisterMobileNumber);
		
		if(fromWallet == null || toWallet == null)
		{
			return "Error: Invalid Wallet Types or Mobile Number...";
		}
		
		if(!currencyFrom.equals(currencyTo))
		{
			BigDecimal convertedAmount = currencyConversionService.convertAmount(amount, currencyFrom, currencyTo);
			
			if(convertedAmount == null)
			{
				return "Error: Currency Conversion Fail...";
			}
			
			amount = convertedAmount;
		}
		
		if(amount.compareTo(BigDecimal.valueOf(100)) > 0 && amount.compareTo(BigDecimal.valueOf(1000)) < 0)
		{
		
		if(fromWallet.getBalance().compareTo(amount) >= 0)
		{
			fromWallet.setBalance(fromWallet.getBalance().subtract(amount));
			
			toWallet.setBalance(toWallet.getBalance().add(amount));
			
			walletRepository.save(fromWallet);
			
			walletRepository.save(toWallet);
			
			WalletReceipt walletReceipt = new WalletReceipt();
			
			walletReceipt.setFromWalletType(fromWalletType);
			
			walletReceipt.setToWalletType(toWalletType);
			
			walletReceipt.setSenderMobileNumber(fromRegisterMobileNumber);
			
			walletReceipt.setReceiverMobileNumber(toRegisterMobileNumber);
			
			walletReceipt.setDateAndTime(LocalDateTime.now());
			
			walletReceipt.setAmountTransfer(amount);
			
			walletReceiptRepository.save(walletReceipt);
			
			return "Success: Money Transferred Succesfully...";
		}
		else
		{
			throw new InsufficientFundException("Insufficient Balance in:"+fromWalletType+" wallet");
		}
	}
		else
		{
			return "Transfer Amount Must Be Greater Than 100 and less than 1000";
		}
		
	}

}
