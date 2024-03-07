package com.mastercard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mastercard.model.WalletReceipt;

public interface WalletReceiptRepository extends JpaRepository<WalletReceipt, Integer>{

}
