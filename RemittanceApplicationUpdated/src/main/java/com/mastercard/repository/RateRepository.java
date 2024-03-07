package com.mastercard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mastercard.model.ConvertedInto;
import com.mastercard.model.CurrencyType;
import com.mastercard.model.Rate;

public interface RateRepository extends JpaRepository<Rate, Integer>{

	public List<Rate> findByCurrencyType(CurrencyType currencyType);
	
	public Rate findByConvertedInto(ConvertedInto convertedInto);
	
	public Rate findByCurrencyTypeAndConvertedInto(CurrencyType currencyType,ConvertedInto convertedInto);
}
