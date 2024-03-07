package com.mastercard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mastercard.model.AmountTransferConfigurationPage;

public interface AmountTransferConfigurationPageRepository extends JpaRepository<AmountTransferConfigurationPage, Integer>{

	public AmountTransferConfigurationPage findFirstByOrderByAmountTransferIdDesc();
}
