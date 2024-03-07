package com.mastercard.service;

import java.util.List;

import com.mastercard.model.Recipent;

public interface IRecipentService {
	
	public Recipent saveRecipent(Recipent recipent);
	
	public List<Recipent> getAllRecipent();
	
	public Recipent updateRecipent(Recipent recipent);
	
	public Recipent findRecipentById(int id);
	
	public String deleteRecipentById(int id);

}
