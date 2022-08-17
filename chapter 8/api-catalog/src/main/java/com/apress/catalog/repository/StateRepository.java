package com.apress.catalog.repository;

import com.apress.catalog.model.State;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StateRepository extends MongoRepository<State, Long> {
	List<State> findByCode(String code);

	List<State> findAllByCountryId(Long id);
}
