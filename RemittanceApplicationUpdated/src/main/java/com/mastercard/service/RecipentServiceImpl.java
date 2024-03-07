package com.mastercard.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mastercard.model.Recipent;
import com.mastercard.repository.RecipentRepository;
import com.mastercard.resourceNotFound.RecipentNotFound;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RecipentServiceImpl implements IRecipentService{

	
	@Autowired
	private RecipentRepository recipentRepository;
	
	@Override
	public Recipent saveRecipent(Recipent recipent) {
		return recipentRepository.save(recipent);
	}

	@Override
	public List<Recipent> getAllRecipent() {
		return recipentRepository.findAll();
	}

	@Override
	public Recipent updateRecipent(Recipent recipent) {
		return recipentRepository.save(recipent);
	}

	@Override
	public Recipent findRecipentById(int id) {
		Optional<Recipent> optionalRecipent = recipentRepository.findById(id);
		return optionalRecipent.orElseThrow(()-> new RecipentNotFound("Recipent Not Found With Id:"+id));
	}

	@Override
	public String deleteRecipentById(int id) {
		recipentRepository.deleteById(id);
		return "Recipent Deleted Successfully with Id:"+id;
	}

}
