package com.apress.catalog.service;

import com.apress.catalog.dto.CountryDTO;
import com.apress.catalog.model.Country;
import com.apress.catalog.model.State;
import com.apress.catalog.repository.StateRepository;
import com.apress.catalog.repository.impl.CustomCountryRepositoryImpl;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CountryService {

	CustomCountryRepositoryImpl countryRepository;

	StateRepository stateRepository;

	MapperFacade mapper;
	Validator validator;

	@Autowired
	public CountryService(CustomCountryRepositoryImpl countryRepository, StateRepository stateRepository,
						  MapperFacade mapper, Validator validator) {
		this.countryRepository = countryRepository;
		this.stateRepository = stateRepository;
		this.mapper = mapper;
		this.validator = validator;
	}

	public List<CountryDTO> getAll(String name) {
		List<CountryDTO> response = null;

		ExampleMatcher matcher = ExampleMatcher.matching()
				.withIgnorePaths("code")
				.withIncludeNullValues()
				.withMatcher("name", ExampleMatcher.GenericPropertyMatcher.of(ExampleMatcher.StringMatcher.DEFAULT));


		Country entity = new Country();
		entity.setName(name);

		Iterable<Country> country = countryRepository.findAll(Example.of(entity, matcher)).toIterable();

		if(country != null) {
			response = mapper.mapAsList(country, CountryDTO.class);
		}

		return response;
	}


	@Transactional(readOnly = true)
	public CountryDTO getById(Long id) {
		CountryDTO response = null;
		Optional<Country> country = countryRepository.findById(id).blockOptional();
		
		if(country.isPresent()) {
			response = mapper.map(country.get(), CountryDTO.class);
		}
		
		return response;
	}

	public CountryDTO save(CountryDTO currency) {
		return saveInformation(currency);
	}

	public CountryDTO update(CountryDTO currency) {
		return saveInformation(currency);
	}

	@Transactional(isolation = Isolation.DEFAULT)
	public void delete(Long id) throws InterruptedException {
		CountryDTO response = null;
		Optional<Country> country = countryRepository.findById(id).blockOptional();
		List<State> states = (List<State>) stateRepository.findAllByCountryId(country.get().getId()).collectList();

		if(country.isPresent()) {
			country.get().setEnabled(Boolean.FALSE);
			countryRepository.save(country.get());

			Thread.sleep(2000L);

			for (State state: states) {
				state.setEnabled(Boolean.FALSE);
				stateRepository.save(state);
			}
		}
	}

	private CountryDTO saveInformation(CountryDTO country) {
		Country entity = mapper.map(country, Country.class);
		Country savedEntity = countryRepository.save(entity).block();

		Set<ConstraintViolation<Country>> violations = validator.validate(entity);
		if(!violations.isEmpty()) {
			throw new ConstraintViolationException(violations);
		}

		return mapper.map(savedEntity, CountryDTO.class);
	}
}
