package com.mastercard.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "rate")
@Getter
@Setter
public class Rate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int rateId;
	
	private BigDecimal conversionRate;
	
	@ManyToOne
	@JoinColumn(name = "currencyType_Id")
	private CurrencyType currencyType;
	
	@ManyToOne
	@JoinColumn(name = "convertedInto_Id")
	private ConvertedInto convertedInto;
}
