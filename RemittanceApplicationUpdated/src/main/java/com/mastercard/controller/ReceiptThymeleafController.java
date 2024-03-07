package com.mastercard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mastercard.model.Receipt;
import com.mastercard.service.IReceiptService;

@Controller
@RequestMapping("/receipt")
public class ReceiptThymeleafController {

	@Autowired
	private IReceiptService receiptService;
	
	@GetMapping("/bankReceipts")
	public String showAllBankTransferReceipt(Model model)
	{
		List<Receipt> receipt = receiptService.getAllBankReceipt();
		model.addAttribute("receipt",receipt);
		return "receipt";
	}
}
