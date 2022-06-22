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

@Service
public class CountryService {

	CountryRepository countryRepository;

	StateRepository stateRepository;

	MapperFacade mapper;
	Validator validator;

	@Autowired
	public CountryService(CountryRepository countryRepository, StateRepository stateRepository,
						  MapperFacade mapper, Validator validator) {
		this.countryRepository = countryRepository;
		this.stateRepository = stateRepository;
		this.mapper = mapper;
		this.validator = validator;
	}

	@Transactional(readOnly = true)
	public CountryDTO getById(Long id) {
		CountryDTO response = null;
		Optional<Country> country = countryRepository.findById(id);
		
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

	@Transactional(readOnly = true, isolation = Isolation.DEFAULT)
	public void delete(Long id) throws InterruptedException {
		CountryDTO response = null;
		Optional<Country> country = countryRepository.findById(id);
		List<State> states = stateRepository.findAllByCountry(country.get().getId());

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

/*	private final TransactionTemplate transactionTemplate;

	@Autowired
	public CountryService(PlatformTransactionManager transactionManager, CountryRepository countryRepository, StateRepository stateRepository,
						  MapperFacade mapper, Validator validator) {
		this.countryRepository = countryRepository;
		this.stateRepository = stateRepository;
		this.mapper = mapper;
		this.validator = validator;
		this.transactionTemplate = new TransactionTemplate(transactionManager);
		transactionTemplate.setReadOnly(false);
		transactionTemplate.setTimeout(1000);
	}


	public void delete(Long id) throws InterruptedException {
		CountryDTO response = null;
		Optional<Country> country = countryRepository.findById(id);
		List<State> states = stateRepository.findAllByCountry(country.get().getId());

		this.transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			public void doInTransactionWithoutResult(TransactionStatus status) {
				try {
					if(country.isPresent()) {
						country.get().setEnabled(Boolean.FALSE);
						countryRepository.save(country.get());

						Thread.sleep(2000L);

						for (State state: states) {
							state.setEnabled(Boolean.FALSE);
							stateRepository.save(state);
						}
					}
				} catch(NoSuchElementException | InterruptedException ex) {
					status.setRollbackOnly();
				}
			}
		});
	}*/
}
