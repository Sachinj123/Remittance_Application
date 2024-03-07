package com.mastercard.service;

import java.math.BigDecimal;
import java.util.List;

import com.mastercard.model.Rate;

public interface IRateService {

	public Rate saveConversionRate(Rate rate);
	
	public Rate updateConversionRate(Rate rate);
	
	public List<Rate> getAllConversionRateDetails();
	
	public Rate findConversionRateDetailsById(int id);
	
	public String deleteConversionRateDetails(int id);
	
	public void updateRate(int rateId,BigDecimal newRate);
}
