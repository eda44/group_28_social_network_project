package ru.skillbox.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import ru.skillbox.dto.AccountDto;
import ru.skillbox.dto.CityDto;
import ru.skillbox.dto.CountryDto;
import ru.skillbox.model.City;
import ru.skillbox.model.Country;
import ru.skillbox.model.Person;



@Mapper
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    AccountDto personToAccountDto(Person person);


    @Mapping(source = "country", target = "countryId", qualifiedByName = "mapCountry")
    CityDto cityToCityDto(City city);


    CountryDto countryToCountryDto(Country country);


    @Named("mapCountry")
    default Long mapCountry(Country country){
        return country.getId();
    }
}
