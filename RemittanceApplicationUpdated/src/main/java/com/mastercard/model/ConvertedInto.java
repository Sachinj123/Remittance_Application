package com.mastercard.model;

import java.util.List;

import org.hibernate.annotations.Cascade;

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
@Table(name = "currencyConverted")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConvertedInto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotEmpty(message = "Targeted Currency Can't Be Blank...")
	private String targetCurrency;
	
	@OneToMany(mappedBy = "convertedInto",cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Rate> convertedIntoRates;
}
