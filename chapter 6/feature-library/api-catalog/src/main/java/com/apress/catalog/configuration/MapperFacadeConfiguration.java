package com.apress.catalog.configuration;

import com.apress.catalog.dto.CountryDTO;
import com.apress.catalog.dto.StateDTO;
import com.apress.catalog.model.Country;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperFacadeConfiguration {

    @Bean
    public MapperFacade mapperFacade() {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

        mapperFactory.classMap(Country.class, CountryDTO.class)
                .customize(new CustomMapper<Country, CountryDTO>() {
                    @Override
                    public void mapAtoB(Country country, CountryDTO countryDTO, MappingContext context) {
                        super.mapAtoB(country, countryDTO, context);

                        for(StateDTO state : countryDTO.getStates()) {
                            state.setCountry(null);
                        }
                    }
                })
                .byDefault()
                .register();

        return mapperFactory.getMapperFacade();
    }
}
