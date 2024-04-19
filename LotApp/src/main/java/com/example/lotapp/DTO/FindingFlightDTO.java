package com.example.lotapp.DTO;

public record FindingFlightDTO(String from,
                               String to,
                               boolean freeSeats,
                               String departureAirport,
                               String destinationAirport) {
}
