package com.apress.catalog.model;

import org.springframework.data.neo4j.core.schema.*;

import java.util.List;

@Node("Country")
public class Country {

	@Id
	@GeneratedValue
	private Long id;

	@Property("code")
	private String code;

	@Property("name")
	private String name;

	@Property("locale")
	private String locale;

	@Property("timezone")
	private String timezone;

	@Property("enabled")
	private Boolean enabled = Boolean.TRUE;

	@Relationship("currency")
	private Currency currency;

	@Relationship("states")
	private List<State> states;

	public Country() {}

	public Country(Long id, String code, String name, String locale, String timezone, Boolean enabled, Currency currency, List<State> states) {
		this.id = id;
		this.code = code;
		this.name = name;
		this.locale = locale;
		this.timezone = timezone;
		this.enabled = enabled;
		this.currency = currency;
		this.states = states;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public List<State> getStates() {
		return states;
	}

	public void setStates(List<State> states) {
		this.states = states;
	}
}
