package com.mastercard.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mastercard.model.Rate;
import com.mastercard.service.IRateService;

@Controller
@RequestMapping("/rates")
public class RateThymeleafController {

	@Autowired
	private IRateService rateService;
	
	@GetMapping("/update")
	public String updateRates(Model model)
	{
		List<Rate> rates = rateService.getAllConversionRateDetails();
		model.addAttribute("rates",rates);
		return "rate-update";
	}
	
	@PostMapping("/update")
	public String updateRates(@RequestParam Map<String, String> formData)
	{
		for(Map.Entry<String, String> entry : formData.entrySet())
		{
			Integer rateId = Integer.parseInt(entry.getKey());
			BigDecimal newRate = new BigDecimal(entry.getValue());
			rateService.updateRate(rateId, newRate);
		}
		return "redirect:/rates/update?success";
	}
}
