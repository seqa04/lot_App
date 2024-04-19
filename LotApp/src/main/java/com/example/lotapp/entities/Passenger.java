package com.example.lotapp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Passenger {
    @Id
    @GeneratedValue
    private Integer id;

    private String firstName;
    private String lastName;
    private String phoneNumber;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "flight_passengers",
            joinColumns = @JoinColumn(name = "passengerId"),
            inverseJoinColumns = @JoinColumn(name = "flightNumber"))
    private Set<Flight> flights = new HashSet<>();

    public Passenger(String firstName, String lastName, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    @PreRemove
    private void removeGroupsFromUsers() {
        for (Flight f : flights) {
            f.setNumberOfFreeSeats(f.getNumberOfFreeSeats() + 1);
        }
    }

    @Override
    public String toString() {
        return "(\n" +
                "id = " + id + '\n' +
                "firstName = " + firstName + '\n' +
                "lastName = " + lastName + '\n' +
                "phoneNumber = " + phoneNumber + '\n' +
                "   flights = " + flights + '\n' +
                ")";
    }
}
