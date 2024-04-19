package com.example.lotapp.repositories;

import com.example.lotapp.entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {

    Optional<Flight> findByFlightNumber(String flightNumber);

    @Transactional
    @Modifying
    void deleteFlightByFlightNumber(String flightNumber);

    @Transactional
    @Modifying
    @Query(value = "UPDATE FLIGHT SET DEPARTURE_AIRPORT = :departureAirport WHERE FLIGHT_NUMBER = :flightNumber", nativeQuery = true)
    void updateFlightDepartureAirport(String flightNumber, String departureAirport);

    @Transactional
    @Modifying
    @Query(value = "UPDATE FLIGHT SET DESTINATION_AIRPORT = :destinationAirport WHERE FLIGHT_NUMBER = :flightNumber", nativeQuery = true)
    void updateFlightDestinationAirport(String flightNumber, String destinationAirport);

    @Transactional
    @Modifying
    @Query(value = "UPDATE FLIGHT SET DEPARTURE_DATE = :departureDate WHERE FLIGHT_NUMBER = :flightNumber", nativeQuery = true)
    void updateFlightDepartureDate(String flightNumber, LocalDateTime departureDate);
}
