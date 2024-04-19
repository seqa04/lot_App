package com.example.lotapp.services;

import com.example.lotapp.DTO.PassengerDTO;
import com.example.lotapp.entities.Passenger;
import org.springframework.stereotype.Service;

@Service
public class PassengerMapper {
    Passenger toPassenger(PassengerDTO passengerDTO) {
        return new Passenger(passengerDTO.firstName(),
                passengerDTO.lastName(),
                passengerDTO.phoneNumber());
    }
}
