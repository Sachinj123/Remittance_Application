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

import com.mastercard.model.Rate;
import com.mastercard.service.IRateService;

@RestController
@RequestMapping("/conversion-rate")
public class ConversionRateController {

	@Autowired
	private IRateService iRateService;
	
	@GetMapping("/getAll")
	public List<Rate> getAllConversionRate()
	{
		return iRateService.getAllConversionRateDetails();
	}
	
	@PostMapping("/save")
	public ResponseEntity<Rate> addConversionRate(@RequestBody Rate rate)
	{
		Rate saveConversionRate = iRateService.saveConversionRate(rate);
		return ResponseEntity.ok().body(saveConversionRate);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Rate> updateConversionRateDetails(@RequestBody Rate rate)
	{
		Rate updateConversionRate = iRateService.updateConversionRate(rate);
		return ResponseEntity.ok().body(updateConversionRate);
	}
	
	@GetMapping("/get-ConversionRate/{id}")
	public ResponseEntity<Rate> getConversionRateById(@PathVariable int id)
	{
		return ResponseEntity.ok().body(iRateService.findConversionRateDetailsById(id));
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteConversionRateDetailsById(int id)
	{
		return iRateService.deleteConversionRateDetails(id);
	}
}
