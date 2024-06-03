package bitlab.final_project.service;

import bitlab.final_project.dto.CountryCreate;
import bitlab.final_project.dto.CountryEdit;
import bitlab.final_project.dto.CountryView;
import bitlab.final_project.entity.Country;
import bitlab.final_project.mapper.CountryMapper;
import bitlab.final_project.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CountryService {
    private final CountryRepository countryRepository;

    public CountryView getCountryById(Long id) {
        Country country = countryRepository.findById(id).orElse(null);
        return CountryMapper.INSTANCE.toView(country);
    }

    public List<CountryView> getAllCountries() {
        List<Country> countries = countryRepository.findAll();
        return CountryMapper.INSTANCE.toView(countries);
    }

    public CountryView createCountry(CountryCreate countryCreate) {
        Optional<Country> optionalCountry = countryRepository.findByName(countryCreate.getName());
        if (optionalCountry.isPresent()) {
            throw new RuntimeException("Эта страна уже существует!");
        }
        Country country = CountryMapper.INSTANCE.toEntity(countryCreate);
        Country savedCountry = countryRepository.save(country);
        return CountryMapper.INSTANCE.toView(savedCountry);
    }

    public CountryView editCountry(CountryEdit countryEdit) {
        Optional<Country> optionalCountry = countryRepository.findById(countryEdit.getId());
        if (optionalCountry.isPresent()) {
            Country country = optionalCountry.get();
            CountryMapper.INSTANCE.updateCountryFromDto(countryEdit, country);
            Country updatedCountry = countryRepository.save(country);
            return CountryMapper.INSTANCE.toView(updatedCountry);
        } else {
            throw new RuntimeException("Страны с ID: " + countryEdit.getId() + " не найдено!");
        }
    }

    public void deleteCountryById(Long id) {
        countryRepository.deleteById(id);
    }
}
