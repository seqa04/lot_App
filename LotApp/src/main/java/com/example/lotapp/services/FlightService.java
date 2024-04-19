package com.example.lotapp.services;

import com.example.lotapp.DTO.FindingFlightDTO;
import com.example.lotapp.DTO.FlightDTO;
import com.example.lotapp.entities.Flight;
import com.example.lotapp.repositories.FlightRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class FlightService {

    private final FlightRepository repository;
    private final FlightMapper mapper;
    private final FlightValidator validator;
    private final JdbcTemplate jdbcTemplate;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss");
    private final DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public FlightService(FlightRepository repository, FlightMapper mapper, FlightValidator validator, JdbcTemplate jdbcTemplate) {
        this.repository = repository;
        this.mapper = mapper;
        this.validator = validator;
        this.jdbcTemplate = jdbcTemplate;
    }

    public Flight addFlight(FlightDTO flightDTO) {
        validator.validateFlightDTO(flightDTO);
        return repository.save(mapper.toFlight(flightDTO));
    }

    public Flight findByFlightNumber(String flightNumber) {
        return repository.findByFlightNumber(flightNumber).orElseThrow(
                () -> new NoSuchElementException("Flight not found"));
    }

    public List<Flight> findAllFlights() {
        List<Flight> fl = repository.findAll();
        if (fl.isEmpty()) throw new NoSuchElementException("Flights not found");
        return fl;
    }

    public void deleteFlightByFlightNumber(String flightNumber) {
        repository.findByFlightNumber(flightNumber).orElseThrow(
                () -> new NoSuchElementException("Flight with this number dont exist"));
        repository.deleteFlightByFlightNumber(flightNumber);
    }

    public void updateFlightDepartureAirport(String flightNumber, String departureAirport) {
        repository.findByFlightNumber(flightNumber).orElseThrow(
                () -> new NoSuchElementException("Flight with this number dont exist"));
        validator.validateDepartureAirport(departureAirport);
        repository.updateFlightDepartureAirport(flightNumber, departureAirport);
    }

    public void updateFlightDestinationAirport(String flightNumber, String destinationAirport) {
        repository.findByFlightNumber(flightNumber).orElseThrow(
                () -> new NoSuchElementException("Flight with this number dont exist"));
        validator.validateDestinationAirport(destinationAirport);
        repository.updateFlightDestinationAirport(flightNumber, destinationAirport);
    }

    public void updateFlightDepartureDate(String flightNumber, String departureDate) {
        repository.findByFlightNumber(flightNumber).orElseThrow(
                () -> new NoSuchElementException("Flight with this number dont exist"));
        validator.validateDate(departureDate);
        LocalDateTime dateTime = LocalDateTime.parse(departureDate, formatter);
        repository.updateFlightDepartureDate(flightNumber, dateTime);
    }

    public List<Flight> findFlights(FindingFlightDTO dto) {
        StringBuilder query = new StringBuilder("SELECT * FROM FLIGHT WHERE");

        if (dto.freeSeats()) {
            query.append(" number_of_free_seats > 0 AND");
        } else query.append(" 1=1 AND");

        if (dto.departureAirport() != null && !dto.departureAirport().isBlank()) {
            query.append(" departure_airport = '").append(dto.departureAirport()).append("' AND");
        } else query.append(" 1=1 AND");

        if (dto.destinationAirport() != null && !dto.destinationAirport().isBlank()) {
            query.append(" destination_airport = '").append(dto.destinationAirport()).append("' AND");
        } else query.append(" 1=1 AND");

        try {
            if (dto.to() != null && dto.from() != null && !dto.from().isBlank() && !dto.to().isBlank()) {
                query.append(" departure_date between '").append(LocalDateTime.parse(dto.from(), formatter))
                        .append("' AND '").append(LocalDateTime.parse(dto.to(), formatter)).append("'");
            } else if (dto.from() != null && !dto.from().isBlank()) {
                query.append(" departure_date >= '").append(LocalDateTime.parse(dto.from(), formatter)).append("'");
            } else if (dto.to() != null && !dto.to().isBlank()) {
                query.append(" departure_date <= '").append(LocalDateTime.parse(dto.to(), formatter)).append("'");
            } else query.append(" 1=1");
        } catch (DateTimeParseException ex) {
            throw new IllegalArgumentException("Bad data format use: yyyy-MM-dd_HH:mm:ss");
        }
        return jdbcTemplate.query(String.valueOf(query),
                (rs, rowNum) -> new Flight(rs.getString("flight_number"), rs.getString("departure_airport"),
                        rs.getString("destination_airport"), LocalDateTime.parse(rs.getString("departure_date"), formatter1), rs.getInt("number_of_free_seats")));
    }
}
