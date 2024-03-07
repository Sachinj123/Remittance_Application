package com.mastercard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mastercard.model.Receipt;
import com.mastercard.repository.ReceiptRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ReceiptServiceImpl implements IReceiptService{

	@Autowired
	private ReceiptRepository receiptRepository;

	@Override
	public List<Receipt> getAllBankReceipt() {
		return receiptRepository.findAll();
	}
	
	
}
