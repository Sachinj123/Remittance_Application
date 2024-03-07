package com.mastercard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mastercard.model.Receipt;

public interface ReceiptRepository extends JpaRepository<Receipt, Integer>{

}
