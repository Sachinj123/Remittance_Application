package com.mastercard.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "amount_Transferconfigurationpage")
@Getter
@Setter
@NoArgsConstructor
public class AmountTransferConfigurationPage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer amountTransferId;
	
	private BigDecimal minAmountTransfer;
	
	private BigDecimal maxAmountTransfer;

	public AmountTransferConfigurationPage(BigDecimal minAmountTransfer, BigDecimal maxAmountTransfer) {
		super();
		this.minAmountTransfer = minAmountTransfer;
		this.maxAmountTransfer = maxAmountTransfer;
	}
	
	
}
