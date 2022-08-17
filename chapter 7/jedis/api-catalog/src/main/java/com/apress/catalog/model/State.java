package com.apress.catalog.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("state")
public class State {

	@Id
	private Long id;

	private String code;

	private String name;

	private Boolean enabled = Boolean.TRUE;

	private Country country;

	public State() {}

	public State(Long id, String code, String name, Boolean enabled, Country country) {
		this.id = id;
		this.code = code;
		this.name = name;
		this.enabled = enabled;
		this.country = country;
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

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
}
