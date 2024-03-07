package com.mastercard.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mastercard.model.AmountTransferConfigurationPage;
import com.mastercard.service.IAmountTransferConfigurationPageService;

@RestController
@RequestMapping("/amount-transfer")
public class AmountTransferConfigurationPageController {

	@Autowired
	private IAmountTransferConfigurationPageService amountTransferConfigurationPageService;
	
	@GetMapping("/get-amount")
	public List<AmountTransferConfigurationPage> getAllAmountTransferConfigurationPage()
	{
		return amountTransferConfigurationPageService.getAllTransferConfigurationPage();
	}
	
	@PostMapping("/save-amount")
	public ResponseEntity<AmountTransferConfigurationPage> 
		saveAmountTransferConfigurationPage(@RequestBody AmountTransferConfigurationPage amountTransferConfigurationPage)
	{
		AmountTransferConfigurationPage saveAmountTransferConfigurationPage = amountTransferConfigurationPageService
				.saveAmountTransferConfigurationPage(amountTransferConfigurationPage);
		
		return ResponseEntity.ok().body(saveAmountTransferConfigurationPage);
	}
	
	@DeleteMapping("/delete/{id}")
	public String deletesaveAmountTransferConfigurationPage(@PathVariable int id)
	{
		return amountTransferConfigurationPageService.deleteAmountTransferConfigurationPage(id);
	}
	
	/*
	 * @PutMapping("/update") public ResponseEntity<AmountTransferConfigurationPage>
	 * updateamountTransferConfigurationPageService(@RequestBody
	 * AmountTransferConfigurationPage amountTransferConfigurationPage) {
	 * AmountTransferConfigurationPage updateAmountTransferConfigurationPage =
	 * amountTransferConfigurationPageService
	 * .updateAmountTransferConfigurationPage(amountTransferConfigurationPage);
	 * 
	 * return ResponseEntity.ok().body(updateAmountTransferConfigurationPage); }
	 */
	
	@GetMapping("/get-amount/{id}")
	public Optional<AmountTransferConfigurationPage> getAmountTransferConfigurationPageById(@PathVariable int id)
	{
		return amountTransferConfigurationPageService.findAmountTransferConfigurationPageById(id);
	}
}
