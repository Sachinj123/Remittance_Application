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

import com.mastercard.model.Recipent;
import com.mastercard.service.IRecipentService;

@RestController
@RequestMapping("/recipent")
public class RecipentController {

	@Autowired
	private IRecipentService recipentService;
	
	@PostMapping("/save")
	public ResponseEntity<Recipent> createRecipent(@RequestBody Recipent recipent)
	{
		Recipent saveRecipent = recipentService.saveRecipent(recipent);
		return ResponseEntity.ok().body(saveRecipent);
	}
	
	@GetMapping("/getallrecipent")
	public List<Recipent> getAllRecipent()
	{
		return recipentService.getAllRecipent();
	}
	
	@GetMapping("/getrecipent/{id}")
	public Recipent getRecipentById(@PathVariable Integer id)
	{
		return recipentService.findRecipentById(id);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Recipent> updateRecipentDetails(@RequestBody Recipent recipent)
	{
		Recipent updateRecipent = recipentService.updateRecipent(recipent);
		return ResponseEntity.ok().body(updateRecipent);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteRecipentById(@PathVariable Integer id)
	{
		recipentService.deleteRecipentById(id);
	}
}
