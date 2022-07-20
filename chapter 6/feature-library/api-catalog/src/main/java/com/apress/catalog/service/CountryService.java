package com.apress.catalog.service;

import com.apress.catalog.dto.CountryDTO;
import com.apress.catalog.model.Country;
import com.apress.catalog.model.State;
import com.apress.catalog.repository.CountryRepository;
import com.apress.catalog.repository.StateRepository;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.ff4j.FF4j;

@Service
public class CountryService {

	private static final String FEATURE_NEW_COUNTRY_FIND_BY_ID = "use_new_country_by_id";
	CountryRepository countryRepository;

	StateRepository stateRepository;

	MapperFacade mapper;
	Validator validator;

	FF4j ff4j;

	@Autowired
	public CountryService(CountryRepository countryRepository, StateRepository stateRepository,
						  MapperFacade mapper, Validator validator,
						  FF4j ff4j) {
		this.countryRepository = countryRepository;
		this.stateRepository = stateRepository;
		this.mapper = mapper;
		this.validator = validator;
		this.ff4j = ff4j;
	}

	@Transactional(readOnly = true)
	public CountryDTO getById(Long id) {
		CountryDTO response = null;
		Optional<Country> country = Optional.empty();

		if (!ff4j.getPropertiesStore().existProperty(FEATURE_NEW_COUNTRY_FIND_BY_ID)
			|| !ff4j.getPropertiesStore().readProperty(FEATURE_NEW_COUNTRY_FIND_BY_ID).asBoolean()) {
			country = countryRepository.findById(id);
		} else {
			country = countryRepository.findByIdUsingQuery(id);
		}
		
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
		Optional<Country> country = countryRepository.findById(id);
		List<State> states = stateRepository.findAllByCountryId(country.get().getId());

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
		Country savedEntity = countryRepository.save(entity);

		Set<ConstraintViolation<Country>> violations = validator.validate(entity);
		if(!violations.isEmpty()) {
			throw new ConstraintViolationException(violations);
		}

		return mapper.map(savedEntity, CountryDTO.class);
	}
}
