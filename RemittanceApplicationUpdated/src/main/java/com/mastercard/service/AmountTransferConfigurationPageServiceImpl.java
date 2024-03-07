package com.mastercard.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mastercard.model.AmountTransferConfigurationPage;
import com.mastercard.repository.AmountTransferConfigurationPageRepository;
import com.mastercard.resourceNotFound.InvalidAmountTransferConfigurationException;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AmountTransferConfigurationPageServiceImpl implements IAmountTransferConfigurationPageService{

	@Autowired
	private AmountTransferConfigurationPageRepository amountTransferConfigurationPageRepository;
	
	@Override
	public AmountTransferConfigurationPage saveAmountTransferConfigurationPage(
			AmountTransferConfigurationPage amountTransferConfigurationPage) {
		return amountTransferConfigurationPageRepository.save(amountTransferConfigurationPage);
	}

	@Override
	public List<AmountTransferConfigurationPage> getAllTransferConfigurationPage() {
		return amountTransferConfigurationPageRepository.findAll();
	}

	@Override
	public Optional<AmountTransferConfigurationPage> findAmountTransferConfigurationPageById(int id) {
		Optional<AmountTransferConfigurationPage> optionalAmountTransferConfigurationPage
									= amountTransferConfigurationPageRepository.findById(id);
		return Optional.ofNullable(optionalAmountTransferConfigurationPage.orElseThrow(()-> new InvalidAmountTransferConfigurationException("Invalid Amount Transfer Configuration with Id:"+id)));
	}

	@Override
	public void updateAmountTransferConfigurationPage(
			AmountTransferConfigurationPage amountTransferConfigurationPage) {
			
		AmountTransferConfigurationPage currentConfiguration = getAmountTransferConfiguration();
		 
		currentConfiguration.setMinAmountTransfer(amountTransferConfigurationPage.getMinAmountTransfer());
		
		currentConfiguration.setMinAmountTransfer(amountTransferConfigurationPage.getMaxAmountTransfer());
		
		amountTransferConfigurationPageRepository.save(currentConfiguration);
	}

	@Override
	public String deleteAmountTransferConfigurationPage(int id) {
		amountTransferConfigurationPageRepository.deleteById(id);
		return "Amount Configuration Deleted Successfully....";
	}

	@Override
	public AmountTransferConfigurationPage getAmountTransferConfiguration() {
		return amountTransferConfigurationPageRepository.findAll().stream().findFirst().orElse(new AmountTransferConfigurationPage(BigDecimal.ZERO,BigDecimal.ZERO));
	}

	@Override
	public AmountTransferConfigurationPage getLatestConfiguration() {
		return amountTransferConfigurationPageRepository.findFirstByOrderByAmountTransferIdDesc();
	}

}
