package com.apress.catalog.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.apress.catalog.model.Currency;

public class CurrencySpecification implements Specification<Currency> {
	
	private static final long serialVersionUID = 2753473399996931822L;
	
	Currency entity;

    public CurrencySpecification(Currency entity) {
        this.entity = entity;
    }
	
	@Override
	public Predicate toPredicate(Root<Currency> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		
        //create a new predicate list
        List<Predicate> predicates = new ArrayList<>();
		
	    CriteriaQuery<Currency> cq = builder.createQuery(Currency.class);

	    // You need to define the main entity
	    Root<Currency> currency = cq.from(Currency.class); 

	    // Define all the conditions of the query
	    Predicate codePredicate = builder.equal(currency.get("code"), entity.getCode());
	    
	    predicates.add(codePredicate);

	    return builder.and(predicates.toArray(new Predicate[0]));
	}
}
