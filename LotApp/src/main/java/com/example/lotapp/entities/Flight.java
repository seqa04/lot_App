package com.example.lotapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Flight {

    @Id
    private String flightNumber;

    private String departureAirport;
    private String destinationAirport;
    private LocalDateTime departureDate;
    private Integer numberOfFreeSeats;

    @ManyToMany(mappedBy = "flights", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Passenger> passengers = new HashSet<>();

    public Flight(String flightNumber, String departureAirport, String destinationAirport, LocalDateTime departureDate, Integer numberOfFreeSeats) {
        this.flightNumber = flightNumber;
        this.departureAirport = departureAirport;
        this.destinationAirport = destinationAirport;
        this.departureDate = departureDate;
        this.numberOfFreeSeats = numberOfFreeSeats;
    }

    public String toString() {
        return "(" + '\n' +
                "flightNumber = " + flightNumber + '\n' +
                "departureAirport = " + departureAirport + '\n' +
                "destinationAirport = " + destinationAirport + '\n' +
                "departureDate = " + departureDate + '\n' +
                "numberFreeSeats = " + numberOfFreeSeats + '\n' +
                ')';
    }

    @PreRemove
    private void removeGroupsFromUsers() {
        for (Passenger p : passengers) {
            p.getFlights().remove(this);
        }
    }
}
