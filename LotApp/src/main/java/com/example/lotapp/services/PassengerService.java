package com.example.lotapp.services;

import com.example.lotapp.DTO.PassengerDTO;
import com.example.lotapp.entities.Flight;
import com.example.lotapp.entities.Passenger;
import com.example.lotapp.repositories.FlightRepository;
import com.example.lotapp.repositories.PassengerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PassengerService {

    private final PassengerRepository pasRepository;
    private final FlightRepository flRepository;
    private final PassengerMapper mapper;
    private final PassengerValidator validator;

    public PassengerService(PassengerRepository repository, FlightRepository flRepository, PassengerMapper mapper, PassengerValidator validator) {
        this.pasRepository = repository;
        this.flRepository = flRepository;
        this.mapper = mapper;
        this.validator = validator;
    }

    public Passenger addPassenger(PassengerDTO passengerDTO) {
        validator.validate(passengerDTO);
        return pasRepository.save(mapper.toPassenger(passengerDTO));
    }

    public Passenger findPassengerById(Integer id) {
        return pasRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Passenger not found"));
    }

    public List<Passenger> findAllPassengers() {
        List<Passenger> pas = pasRepository.findAll();
        if (pas.isEmpty()) throw new NoSuchElementException("Passengers not found");
        return pas;
    }

    public void deletePassengerById(Integer id) {
        pasRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Passenger with this id dont exist"));
        pasRepository.deleteById(id);
    }

    public Passenger addPassengerToFlight(Integer passengerId, String flightNumber) {
        Flight fl = flRepository.findByFlightNumber(flightNumber).orElseThrow(
                () -> new NoSuchElementException("Flight with this number dont exist"));
        Passenger pas = pasRepository.findById(passengerId).orElseThrow(
                () -> new NoSuchElementException("Passenger with this id dont exist"));
        if (pas.getFlights().contains(fl))
            throw new RuntimeException("The passenger has already been assigned to this flight");
        Integer nOfSeats = fl.getNumberOfFreeSeats();
        if (nOfSeats == 0) throw new RuntimeException("No seats available");
        fl.setNumberOfFreeSeats(nOfSeats - 1);
        pas.getFlights().add(fl);
        flRepository.save(fl);
        return pasRepository.save(pas);
    }

    public void updatePassengerPhoneNumber(Integer id, String phoneNumber) {
        pasRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Passenger with this id dont exist"));
        validator.validatePhoneNumber(phoneNumber);
        pasRepository.updatePassengerPhoneNumber(id, phoneNumber);
    }

    public void updatePassengerFirstName(Integer id, String firstName) {
        pasRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Passenger with this id dont exist"));
        validator.validateFirsName(firstName);
        pasRepository.updatePassengerFirstName(id, firstName);
    }

    public void updatePassengerLastName(Integer id, String lastName) {
        pasRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Passenger with this id dont exist"));
        validator.validateLastName(lastName);
        pasRepository.updatePassengerLastName(id, lastName);
    }
}
