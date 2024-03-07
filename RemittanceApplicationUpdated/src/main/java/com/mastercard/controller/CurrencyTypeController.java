package com.mastercard.controller;

import java.util.List;

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

import com.mastercard.model.CurrencyType;
import com.mastercard.service.ICurrencyType;

@RestController
@RequestMapping("/currency")
public class CurrencyTypeController {

	@Autowired
	private ICurrencyType currencyType;
	
	@GetMapping("/getAll")
	public List<CurrencyType> getAllCurrencyDetails()
	{
		return currencyType.getAllCurrencyDetails();
	}
	
	
	@PostMapping("/save")
	public ResponseEntity<CurrencyType> addCurrenyDetails(@RequestBody CurrencyType currencyType)
	{
		CurrencyType saveCurrencyType = this.currencyType.saveCurrency(currencyType);
		
		return ResponseEntity.ok().body(saveCurrencyType);
	}
	
	
	@PutMapping("/update")
	public ResponseEntity<CurrencyType> updateCurrencyDetails(@RequestBody CurrencyType currencyType)
	{
		CurrencyType updateCurrencyType = this.currencyType.updateCurrency(currencyType);
		return ResponseEntity.ok().body(updateCurrencyType);
	}
	
	@GetMapping("/getCurrencyDetails/{id}")
	public ResponseEntity<CurrencyType> getCurrencyDetailsById(@PathVariable int id)
	{
		return ResponseEntity.ok().body(currencyType.findCurrencyDetailsById(id));
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteCurrencyDetailsById(@PathVariable int id)
	{
		return ResponseEntity.ok().body(currencyType.deleteCurrencyDetails(id));
	}
	
	
}
