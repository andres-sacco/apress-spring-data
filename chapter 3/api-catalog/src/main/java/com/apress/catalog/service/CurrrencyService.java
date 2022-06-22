package com.apress.catalog.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apress.catalog.dto.CurrencyDTO;
import com.apress.catalog.model.Currency;
import com.apress.catalog.repository.CurrencyRepository;

import ma.glasnost.orika.MapperFacade;

@Service
public class CurrrencyService {

	CurrencyRepository repository;
	MapperFacade mapper;
	
	@Autowired
	public CurrrencyService(CurrencyRepository repository, MapperFacade mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}
	
	public CurrencyDTO getById(Long id) {
		CurrencyDTO response = null;
		Optional<Currency> currency = repository.findById(id);
		
		if(currency.isPresent()) {
			response = mapper.map(currency.get(), CurrencyDTO.class);
		}
		
		return response;
	}

	public CurrencyDTO save(CurrencyDTO currencyDTO) {
		return currencyDTO;
	}

	public CurrencyDTO update(CurrencyDTO currencyDTO) {
		return currencyDTO;
	}

	public void delete(Long id) {
		// TODO Auto-generated method stub
	}
}
