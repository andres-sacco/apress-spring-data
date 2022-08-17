package com.apress.catalog.repository;

import com.apress.catalog.model.Currency;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurrencyRepository extends MongoRepository<Currency, Long> {
	// General queries
	List<Currency> findByCode(String code);
	List<Currency> findByCodeAndDescription(String code, String description);

	// Order queries
	List<Currency> findByDescriptionOrderByCodeAsc(String description);
	List<Currency> findByDescriptionOrderByCodeDesc(String description);
}
