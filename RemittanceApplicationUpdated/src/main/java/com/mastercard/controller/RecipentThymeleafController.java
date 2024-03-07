package com.mastercard.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mastercard.model.Recipent;
import com.mastercard.model.RecipentBankAccount;
import com.mastercard.repository.RecipentRepository;
import com.mastercard.resourceNotFound.RecipentNotFound;
import com.mastercard.service.IRecipentBankAccountService;
import com.mastercard.service.IRecipentService;

@Controller
@RequestMapping("/recipients")
public class RecipentThymeleafController {

	@Autowired
	private RecipentRepository recipentRepository;
	
	@Autowired
	private IRecipentService recipentService;
	
	@Autowired
	private IRecipentBankAccountService recipentBankAccountService;
	
	@GetMapping
	public String getAllRecipients(Model model)
	{
		List<Recipent> recipients = recipentRepository.findAll();
		model.addAttribute("recipients",recipients);
		return "recipient-list";
	}
	
	@GetMapping("/{recipientId}/bank-accounts")
	public ResponseEntity<List<RecipentBankAccount>> getRecipientBankAccount(@PathVariable int recipientId)
	{
		Optional<Recipent> recipientOptional = recipentRepository.findById(recipientId);
		if(recipientOptional.isEmpty())
		{
			return ResponseEntity.notFound().build();
		}
		
		Recipent recipent = recipientOptional.get();
		return ResponseEntity.ok(recipent.getRecipentBankAccounts());
	}
	
	
	@GetMapping("/add")
	public String showAddForm(Model model)
	{
		model.addAttribute("recipient",new Recipent());
		return "add-recipient";
	}
	
	@PostMapping("/add")
	public String addRecipient(@ModelAttribute Recipent recipent)
	{
		recipentRepository.save(recipent);
		return "redirect:/recipients";
	}
	
	@GetMapping("/edit/{id}")
	public String showEditForm(@PathVariable("id") Integer id,Model model)
	{
		Recipent recipient = recipentRepository.findById(id)
				.orElseThrow(()-> new RecipentNotFound("Recipent Not Found with Id:"+id));
		model.addAttribute("recipient",recipient);
		return "edit-recipient";
	}
	
	@PostMapping("/edit/{id}")
	public String updateRecipient(@PathVariable("id") Integer id,@ModelAttribute Recipent recipient)
	{
		recipient.setRecipentId(id);
		recipentRepository.save(recipient);
		return "redirect:/recipients";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteRecipient(@PathVariable("id") Integer id)
	{
		recipentRepository.deleteById(id);
		return "redirect:/recipients";
	}
}
