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

import com.mastercard.model.ConvertedInto;
import com.mastercard.service.IConvertedInto;

@RestController
@RequestMapping("/converted")
public class ConvertedIntoController {

	@Autowired
	private IConvertedInto iConvertedInto;
	
	@GetMapping("/getAll")
	public List<ConvertedInto> getAllConvertedDetails()
	{
		return iConvertedInto.getAllConvertedIntoDetails();
	}
	
	@PostMapping("/save")
	public ResponseEntity<ConvertedInto> addConvertedIntoDetails(@RequestBody ConvertedInto convertedInto)
	{
		ConvertedInto saveConvertedInto = iConvertedInto.saveConvertedInto(convertedInto);
		return ResponseEntity.ok().body(saveConvertedInto);
	}
	
	@GetMapping("/getConverted/{id}")
	public ResponseEntity<ConvertedInto> getDetailsById(@PathVariable int id)
	{
		return ResponseEntity.ok().body(iConvertedInto.findConvertedIntoById(id));
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteConvetedIntoDetails(@PathVariable int id)
	{
		return iConvertedInto.deleteConvertedIntoDetails(id);
	}
	
	@PutMapping("/update")
	public ResponseEntity<ConvertedInto> updateConvertedIntoDetails(@RequestBody ConvertedInto convertedInto)
	{
		ConvertedInto updateConvertedInto = iConvertedInto.updateConvertedInto(convertedInto);
		return ResponseEntity.ok().body(updateConvertedInto);
	}
}
