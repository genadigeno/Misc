package geno.mapstruct.fundamentals.mapper;

import geno.mapstruct.fundamentals.dto.CarDto;
import geno.mapstruct.fundamentals.dto.PersonDto;
import geno.mapstruct.fundamentals.model.Car;
import geno.mapstruct.fundamentals.model.Person;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface CarMapper {

    @Mapping(source = "make", target = "manufacturer")
    @Mapping(source = "numberOfSeats", target = "seatCount")
    CarDto carToCarDto(Car car);

    @Mapping(source = "name", target = "fullName")
    PersonDto personToPersonDto(Person person);
}
