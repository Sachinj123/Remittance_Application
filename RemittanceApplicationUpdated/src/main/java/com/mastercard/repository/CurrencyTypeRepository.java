package com.mastercard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mastercard.model.CurrencyType;

public interface CurrencyTypeRepository extends JpaRepository<CurrencyType, Integer> {

}
