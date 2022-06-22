package com.apress.catalog.service;

import com.apress.catalog.dto.CurrencyDTO;
import com.apress.catalog.model.Currency;
import com.apress.catalog.repository.CurrencyRepository;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Optional;
import java.util.Set;

@Service
public class CurrrencyService {

	CurrencyRepository repository;
	MapperFacade mapper;
	Validator validator;
	
	@Autowired
	public CurrrencyService(CurrencyRepository repository, MapperFacade mapper, Validator validator) {
		this.repository = repository;
		this.mapper = mapper;
		this.validator = validator;
	}
	
	public CurrencyDTO getById(Long id) {
		CurrencyDTO response = null;
		Optional<Currency> currency = repository.findById(id);
		
		if(currency.isPresent()) {
			response = mapper.map(currency.get(), CurrencyDTO.class);
		}
		
		return response;
	}

	public CurrencyDTO save(CurrencyDTO currency) {
		return saveInformation(currency);
	}

	public CurrencyDTO update(CurrencyDTO currency) {
		return saveInformation(currency);
	}

	public void delete(Long id) {
		CurrencyDTO response = null;
		Optional<Currency> currency = repository.findById(id);

		if(currency.isPresent()) {
			currency.get().setEnabled(Boolean.FALSE);
			repository.save(currency.get());
		}
	}

	private CurrencyDTO saveInformation(CurrencyDTO currency) {
		Currency entity = mapper.map(currency, Currency.class);

		Set<ConstraintViolation<Currency>> violations = validator.validate(entity);
		if(!violations.isEmpty()) {
			throw new ConstraintViolationException(violations);
		}

		Currency savedEntity = repository.save(entity);

		return mapper.map(savedEntity, CurrencyDTO.class);
	}
}
