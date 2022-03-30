package com.apress.catalog.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.apress.catalog.model.Currency;

@Repository
public interface CurrencyRepository extends CrudRepository<Currency, Long> {

}
