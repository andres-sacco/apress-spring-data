package com.apress.catalog.repository;

import com.apress.catalog.model.State;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface StateRepository extends ReactiveMongoRepository<State, Long> {
	Flux<State> findByCode(String code);

	Flux<State> findAllByCountryId(Long id);
}
