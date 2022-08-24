package com.apress.catalog.repository;

import com.apress.catalog.model.State;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StateRepository extends CrudRepository<State, Long> {
	List<State> findByCode(String code);

	List<State> findAllByCountryId(UUID id);
}
