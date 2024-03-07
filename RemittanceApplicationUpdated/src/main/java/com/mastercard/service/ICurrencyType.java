package com.mastercard.service;

import java.util.List;

import com.mastercard.model.CurrencyType;

public interface ICurrencyType {

	public CurrencyType saveCurrency(CurrencyType currencyType);
	
	public CurrencyType updateCurrency(CurrencyType currencyType);
	
	public List<CurrencyType> getAllCurrencyDetails();
	
	public CurrencyType findCurrencyDetailsById(int id);
	
	public String deleteCurrencyDetails(int id);
}
