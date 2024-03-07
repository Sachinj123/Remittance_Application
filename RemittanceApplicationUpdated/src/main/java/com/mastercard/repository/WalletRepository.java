package com.mastercard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mastercard.enums.WalletType;
import com.mastercard.model.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, Integer>{
	
	public Wallet findByWalletTypeAndRegisteredMobileNumber(WalletType walletType,String registeredMobileNumber);
	
	public Wallet findByWalletType(WalletType walletType);

}
