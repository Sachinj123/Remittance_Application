package com.mastercard.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mastercard.model.Rate;
import com.mastercard.service.ICurrencyConversionService;

@RestController
@RequestMapping("/currencyConversion")
public class CurrencyConversionController {

	@Autowired
	private ICurrencyConversionService currencyConversionService;
	
	@GetMapping("/convert")
	public BigDecimal convertAmount(@RequestParam BigDecimal amount,
			@RequestParam Integer currencyTypeId,@RequestParam Integer convertedIntoId)
	{
		return currencyConversionService.convertAmount(amount, currencyTypeId, convertedIntoId);
	}
	
	@GetMapping("/getAllRates")
	public List<Rate> getAllRates(@RequestParam Integer currencyTypeId)
	{
		return currencyConversionService.fetchAllRatesByCurrencyType(currencyTypeId);
	}
	
	
	
}
