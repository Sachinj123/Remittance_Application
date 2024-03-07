package com.mastercard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.mastercard.model.AmountTransferConfigurationPage;
import com.mastercard.service.IAmountTransferConfigurationPageService;

@Controller
public class AmountTransferConfigurationController {

	@Autowired
	private IAmountTransferConfigurationPageService amountTransferConfigurationPageService;
	
	@GetMapping("/updateTransferLimits")
	public String updateTransferLimitsForm(Model model)
	{
		model.addAttribute("configuration",amountTransferConfigurationPageService.getLatestConfiguration());
		
		return "amountTransfer";
	}
	
	@PostMapping("updateTransferLimits")
	public String updateTransferLimits(@ModelAttribute AmountTransferConfigurationPage configuration,BindingResult result)
	{
		if(result.hasErrors())
		{
			return "amountTransfer";
		}
		amountTransferConfigurationPageService.saveAmountTransferConfigurationPage(configuration);
		return "Values are Updated Successfully...";
	}
	
}
