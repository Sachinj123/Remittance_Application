package com.mastercard.service;

import java.math.BigDecimal;
import java.util.List;

import com.mastercard.model.Rate;

public interface ICurrencyConversionService {
	
	public BigDecimal convertAmount(BigDecimal amount,Integer currencyTypeId, Integer convertedTypeId);
	
	public List<Rate> fetchAllRatesByCurrencyType(Integer currencyTypeId);
	
}
