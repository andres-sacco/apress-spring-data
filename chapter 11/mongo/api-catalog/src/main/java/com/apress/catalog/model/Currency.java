package com.apress.catalog.model;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Document(value = "currency")
public class Currency extends Base {

	@NotBlank(message = "Code is mandatory")
	private String code;

	@NotBlank(message = "Symbol is mandatory")
	private String symbol;

	@NotBlank(message = "Description is mandatory")
	private String description;
	@NotNull(message = "The state of the currency is mandatory")
	private Boolean enabled = Boolean.TRUE;

	@Min(value = 1, message = "The minimum value is 1")
	@Max(value = 5 , message = "The maximum value is 5")
	private int decimalPlaces;

	public Currency() {}
	
	public Currency(Long id, String code, String description, Boolean enabled, int decimalPlaces, String symbol) {
		super(id);
		this.code = code;
		this.description = description;
		this.enabled = enabled;
		this.decimalPlaces = decimalPlaces;
		this.symbol = symbol;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Boolean getEnabled() {
		return enabled;
	}
	
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
	public int getDecimalPlaces() {
		return decimalPlaces;
	}
	
	public void setDecimalPlaces(int decimalPlaces) {
		this.decimalPlaces = decimalPlaces;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
}
