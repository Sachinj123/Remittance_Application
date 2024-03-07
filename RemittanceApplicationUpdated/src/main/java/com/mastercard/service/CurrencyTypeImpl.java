package com.mastercard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mastercard.model.CurrencyType;
import com.mastercard.repository.CurrencyTypeRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CurrencyTypeImpl implements ICurrencyType{

	@Autowired
	private CurrencyTypeRepository currencyTypeRepository;
	
	@Override
	public CurrencyType saveCurrency(CurrencyType currencyType) {
		return currencyTypeRepository.save(currencyType);
	}

	@Override
	public CurrencyType updateCurrency(CurrencyType currencyType) {
		return currencyTypeRepository.save(currencyType);
	}

	@Override
	public List<CurrencyType> getAllCurrencyDetails() {
		return currencyTypeRepository.findAll();
	}

	@Override
	public CurrencyType findCurrencyDetailsById(int id) {
		return currencyTypeRepository.findById(id).orElseThrow(()-> new RuntimeException("Currency with:"+id+" is Not Found..."));
	}

	@Override
	public String deleteCurrencyDetails(int id) {
		 currencyTypeRepository.deleteById(id);
		 return "Currency Type Deleted Successfully with Id:"+id;
	}

}
