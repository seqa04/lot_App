package com.example.lotapp.services;

import com.example.lotapp.DTO.FlightDTO;
import com.example.lotapp.repositories.FlightRepository;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class FlightValidator {

    private final FlightRepository repository;

    public FlightValidator(FlightRepository repository) {
        this.repository = repository;
    }

    public void validateFlightDTO(FlightDTO dto) {

        validateFlightNumber(dto.flightNumber());

        validateDepartureAirport(dto.departureAirport());

        validateDestinationAirport(dto.destinationAirport());

        validateNumberOfFreeSeats(dto.numberOfFreeSeats());

        validateDate(dto.departureDate());

    }

    public void validateFlightNumber(String flightNumber) {
        if (flightNumber == null || flightNumber.isBlank()) {
            throw new IllegalArgumentException("Flight number must not be empty");
        }

        Pattern pattern = Pattern.compile("^LO\\d{1,4}$");
        Matcher matcher = pattern.matcher(flightNumber);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Flight number must start with letters LO then from 2 to 4 digits");
        }

        if (repository.findByFlightNumber(flightNumber).isPresent()) {
            throw new IllegalArgumentException("Flight already exist");
        }
    }

    public void validateDepartureAirport(String departureAirport) {
        if (departureAirport == null || departureAirport.isBlank()) {
            throw new IllegalArgumentException("Departure airport must not be empty");
        }

        Pattern pattern = Pattern.compile("^[a-zA-Z]+$");
        Matcher matcher = pattern.matcher(departureAirport);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Departure airport must contain only small and big letters");
        }
    }

    public void validateDestinationAirport(String destinationAirport) {
        if (destinationAirport == null || destinationAirport.isBlank()) {
            throw new IllegalArgumentException("Destination airport must not be empty");
        }

        Pattern pattern = Pattern.compile("^[a-zA-Z]+$");
        Matcher matcher = pattern.matcher(destinationAirport);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Destination airport must contain only small and big letters");
        }
    }

    public void validateNumberOfFreeSeats(Integer numberOfFreeSeats) {
        if (numberOfFreeSeats == null || numberOfFreeSeats <= 0) {
            throw new IllegalArgumentException("Number of vaccines must not be empty and more than zero");
        }
    }

    public void validateDate(String departureDate) {
        if(departureDate == null){
            throw new IllegalArgumentException("Departure date must not be empty");
        }

        Pattern pattern = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}_\\d{2}:\\d{2}:\\d{2}$");
        Matcher matcher = pattern.matcher(departureDate);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Bad data format use: yyyy-MM-dd_HH:mm:ss");
        }
    }

}
