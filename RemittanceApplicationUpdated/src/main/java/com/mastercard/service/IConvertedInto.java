package com.mastercard.service;

import java.util.List;

import com.mastercard.model.ConvertedInto;
import com.mastercard.model.CurrencyType;

public interface IConvertedInto {

	public ConvertedInto saveConvertedInto(ConvertedInto convertedInto);
	
	public ConvertedInto updateConvertedInto(ConvertedInto convertedInto);
	
	public List<ConvertedInto> getAllConvertedIntoDetails();
	
	public ConvertedInto findConvertedIntoById(int id);
	
	public String deleteConvertedIntoDetails(int id);
}
