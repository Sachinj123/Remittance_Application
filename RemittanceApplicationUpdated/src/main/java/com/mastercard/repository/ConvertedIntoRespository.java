package com.mastercard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mastercard.model.ConvertedInto;

public interface ConvertedIntoRespository extends JpaRepository<ConvertedInto, Integer>{

}
