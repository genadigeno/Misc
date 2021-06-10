package geno.mapstruct.fundamentals.mapper;

import geno.mapstruct.fundamentals.dto.CarDto;
import geno.mapstruct.fundamentals.dto.PersonDto;
import geno.mapstruct.fundamentals.model.Car;
import geno.mapstruct.fundamentals.model.Person;

import java.util.ArrayList;
import java.util.Arrays;

public class CarMapperImpl implements CarMapper {

    @Override
    public CarDto carToCarDto(Car car) {
        if (car == null)
            return null;

        CarDto carDto = new CarDto();

        if (car.getFeatures() != null){
            carDto.setFeatures(new ArrayList<String>(Arrays.asList(car.getFeatures())));
        }
        /*carDto.setManufacturer( car.getMake() );
        carDto.setSeatCount( car.getNumberOfSeats() );
        carDto.setDriver( personToPersonDto( car.getDriver() ) );
        carDto.setPrice( String.valueOf( car.getPrice() ) );
        if ( car.getCategory() != null ) {
            carDto.setCategory( car.getCategory().toString() );
        }
        carDto.setEngine( engineToEngineDto( car.getEngine() ) );*/

        return carDto;
    }

    @Override
    public PersonDto personToPersonDto(Person person) {
        return null;
    }
}
