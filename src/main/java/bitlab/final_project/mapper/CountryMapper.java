package bitlab.final_project.mapper;

import bitlab.final_project.dto.CountryCreate;
import bitlab.final_project.dto.CountryEdit;
import bitlab.final_project.dto.CountryView;
import bitlab.final_project.entity.Country;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CountryMapper {
    CountryMapper INSTANCE = Mappers.getMapper(CountryMapper.class);
    CountryView toView(Country country);
    List<CountryView> toView(List<Country> countries);
    Country toEntity(CountryCreate countryCreate);
    void updateCountryFromDto(CountryEdit countryEdit, @MappingTarget Country country);
}
