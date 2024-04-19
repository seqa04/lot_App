package com.example.lotapp.services;

import com.example.lotapp.DTO.FlightDTO;
import com.example.lotapp.entities.Flight;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class FlightMapper {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss");

    public Flight toFlight(FlightDTO flightDTO) {
        return new Flight(flightDTO.flightNumber(),
                flightDTO.departureAirport(),
                flightDTO.destinationAirport(),
                LocalDateTime.parse(flightDTO.departureDate(), formatter),
                flightDTO.numberOfFreeSeats());
    }
}
