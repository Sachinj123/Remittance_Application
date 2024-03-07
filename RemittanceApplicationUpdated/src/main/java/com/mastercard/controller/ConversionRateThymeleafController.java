package com.mastercard.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mastercard.model.ConvertedInto;
import com.mastercard.model.CurrencyType;
import com.mastercard.model.Rate;
import com.mastercard.service.IConvertedInto;
import com.mastercard.service.ICurrencyType;
import com.mastercard.service.IRateService;

@Controller
@RequestMapping("/conversion-rates")
public class ConversionRateThymeleafController {
	
	@Autowired
	private ICurrencyType currencyTypeService;
	
	@Autowired
	private IConvertedInto convertedIntoService;
	
	@Autowired
	private IRateService rateService;
	
	@GetMapping("/edit/{rateId}")
	public String showUpdateForm(@PathVariable("rateId") Integer rateId,Model model)
	{
		Rate rate = rateService.findConversionRateDetailsById(rateId);
		
		List<CurrencyType> currencyTypes = currencyTypeService.getAllCurrencyDetails();
		
		List<ConvertedInto> convertedIntos = convertedIntoService.getAllConvertedIntoDetails();
		
		model.addAttribute("rate",rate);
		
		model.addAttribute("currencyTypes",currencyTypes);
		
		model.addAttribute("convertedIntos",convertedIntos);
		
		return "conversions_rates_edit";
	}
	
	@PostMapping("/update/{ratedId}")
	public String updateConversionRate(@PathVariable ("rateId") Integer rateId, 
			@RequestParam("currencyType") Integer currencyTypeId, 
			@RequestParam("convertedInto") Integer convertedIntoId, @RequestParam("conversionRate") BigDecimal conversionRate)
	{
		Rate rate = rateService.findConversionRateDetailsById(rateId);
		
		CurrencyType currencyType = currencyTypeService.findCurrencyDetailsById(currencyTypeId);
		
		ConvertedInto convertedInto = convertedIntoService.findConvertedIntoById(convertedIntoId);
		
		rate.setCurrencyType(currencyType);
		
		rate.setConvertedInto(convertedInto);
		
		rate.setConversionRate(conversionRate);
		
		rateService.saveConversionRate(rate);
		
		return "redirect;/conversion-rates";
	}

}
