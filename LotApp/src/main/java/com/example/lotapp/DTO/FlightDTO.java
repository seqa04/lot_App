package com.example.lotapp.DTO;

import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDateTime;

public record FlightDTO(
        String flightNumber,
        String departureAirport,
        String destinationAirport,
        String departureDate,
        Integer numberOfFreeSeats
) {
}
