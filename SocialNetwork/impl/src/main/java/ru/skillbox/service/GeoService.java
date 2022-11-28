package ru.skillbox.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.skillbox.dto.CityDto;
import ru.skillbox.dto.CountryDto;
import ru.skillbox.mapper.AccountMapper;
import ru.skillbox.model.City;
import ru.skillbox.model.Country;
import ru.skillbox.repository.CountryRepository;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
public class GeoService {
    private CountryRepository countryRepository;

    @Autowired
    public GeoService(CountryRepository countryRepository){
        this.countryRepository = countryRepository;
    }


    public ResponseEntity<List<CountryDto>> getCountries(){
        List<Country> countryList = countryRepository.findAll();
        List<CountryDto> countryDtoList = new ArrayList<>();
        for(Country country : countryList) {
            CountryDto countryDto = AccountMapper.INSTANCE.countryToCountryDto(country);
            countryDtoList.add(countryDto);
        }
        return ResponseEntity.ok(countryDtoList);
    }

    public ResponseEntity<List<CityDto>> getCities(long countryId) {
        Country country = countryRepository.findById(countryId).get();
        List<City> cityList = country.getCities();
        List<CityDto> cityDtoList = new ArrayList<>();
        cityList.forEach(c -> cityDtoList.add(AccountMapper.INSTANCE.cityToCityDto(c)));
        return ResponseEntity.ok(cityDtoList);
    }
}
