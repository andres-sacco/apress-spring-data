package com.apress.catalog.repository.impl;

import com.apress.catalog.repository.CustomCountryRepository;
import com.apress.catalog.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CustomCountryRepositoryImpl implements CustomCountryRepository {

    private static final Object COUNTRY_KEY = "country";
    final RedisTemplate<Long, Country> countryRedisTemplate;
    private HashOperations hashOperations;

    @Autowired
    public CustomCountryRepositoryImpl(RedisTemplate countryRedisTemplate) {
        this.countryRedisTemplate = countryRedisTemplate;
        this.hashOperations = countryRedisTemplate.opsForHash();
    }

    @Override
    public Country save(Country entity) {
        hashOperations.put(COUNTRY_KEY, entity.getId(), (Object) entity);
        return findById(entity.getId()).get();
    }

    @Override
    public Optional<Country> findById(Long id) {
        return Optional.of((Country) hashOperations.get(COUNTRY_KEY, id));
    }

    @Override
    public void deleteById(Long id) {
        hashOperations.delete(COUNTRY_KEY, id);
    }
}
