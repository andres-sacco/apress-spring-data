package com.apress.catalog.model;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

@Node("Currency")
public class Currency {

	@Id
	private Long id;
	private String code;

	private String symbol;

	private String description;

	private Boolean enabled = Boolean.TRUE;

	private int decimalPlaces;

	private @Relationship Country country;

	public Currency() {}
	
	public Currency(Long id, String code, String description, Boolean enabled, int decimalPlaces, String symbol) {
		this.id = id;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
}
