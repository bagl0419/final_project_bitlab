package bitlab.final_project.controller;

import bitlab.final_project.dto.CountryCreate;
import bitlab.final_project.dto.CountryEdit;
import bitlab.final_project.dto.CountryView;
import bitlab.final_project.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/country")
@RequiredArgsConstructor
public class CountryController {
    private final CountryService countryService;

    @GetMapping("{id}")
    public CountryView getCountry(@PathVariable Long id) {
        return countryService.getCountryById(id);
    }

    @GetMapping
    public List<CountryView> getAllCountries() {
        return countryService.getAllCountries();
    }

    @PostMapping
    public CountryView createCountry(@RequestBody CountryCreate countryCreate) {
        return countryService.createCountry(countryCreate);
    }

    @PutMapping
    public CountryView editCountry(@RequestBody CountryEdit countryEdit) {
        return countryService.editCountry(countryEdit);
    }

    @DeleteMapping("{id}")
    public void deleteCountry(@PathVariable Long id) {
        countryService.deleteCountryById(id);
    }
}
