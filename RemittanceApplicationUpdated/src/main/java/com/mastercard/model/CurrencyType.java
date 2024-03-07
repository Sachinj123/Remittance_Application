package com.mastercard.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "currencyType")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int currencyId;
	
	@NotEmpty(message = "Currency Type Can't Be Blank...")
	private String currencyType;
	
	@OneToMany(mappedBy = "currencyType", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Rate> rates;
	
}
