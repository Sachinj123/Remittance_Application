package com.mastercard.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mastercard.model.Rate;
import com.mastercard.repository.RateRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RateServiceImpl implements IRateService{

	@Autowired
	private RateRepository rateRepository;
	
	@Override
	public Rate saveConversionRate(Rate rate) {
		return rateRepository.save(rate);
	}

	@Override
	public Rate updateConversionRate(Rate rate) {
		return rateRepository.save(rate);
	}

	@Override
	public List<Rate> getAllConversionRateDetails() {
		return rateRepository.findAll();
	}

	@Override
	public Rate findConversionRateDetailsById(int id) {
		return rateRepository.findById(id)
				.orElseThrow(()-> new RuntimeException("Conversion Rate Not Found With Id:"+id));
	}

	@Override
	public String deleteConversionRateDetails(int id) {
		rateRepository.deleteById(id);
		return "Conversion Rate Deleted Successfully with Id:"+id;
	}

	@Override
	public void updateRate(int rateId, BigDecimal newRate) {
		Optional<Rate> optionalRate = rateRepository.findById(rateId);
		if(optionalRate.isPresent())
		{
			Rate rate = optionalRate.get();
			rate.setConversionRate(newRate);
			rateRepository.save(rate);
		}
		else
		{
			throw new RuntimeException("Rate With Id:"+rateId+" not Found");
		}
	}

}
