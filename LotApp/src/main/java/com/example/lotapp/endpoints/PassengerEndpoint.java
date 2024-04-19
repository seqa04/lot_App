package com.example.lotapp.endpoints;

import com.example.lotapp.DTO.PassengerDTO;
import com.example.lotapp.entities.Passenger;
import com.example.lotapp.services.PassengerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("passenger")
public class PassengerEndpoint {

    private final PassengerService service;

    public PassengerEndpoint(PassengerService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public Passenger addPassenger(@RequestBody PassengerDTO passenger) {
        return service.addPassenger(passenger);
    }

    @GetMapping("/{id}")
    public Passenger findPassengerById(@PathVariable Integer id) {
        return service.findPassengerById(id);
    }

    @GetMapping("/all")
    public List<Passenger> findAllPassengers() {
        return service.findAllPassengers();
    }

    @DeleteMapping("/{id}")
    public String deletePassengerById(@PathVariable Integer id) {
        service.deletePassengerById(id);
        return "Done";
    }

    @PutMapping("/{id}/toFlight/{flightNumber}")
    public Passenger addPassengerToFlight(@PathVariable Integer id, @PathVariable String flightNumber) {
        return service.addPassengerToFlight(id, flightNumber);
    }

    @PutMapping("{id}/updatePhoneNumber/{phoneNumber}")
    public String updatePassengerPhoneNumber(@PathVariable Integer id, @PathVariable String phoneNumber) {
        service.updatePassengerPhoneNumber(id, phoneNumber);
        return "Done";
    }

    @PutMapping("{id}/updateFirstName/{firstName}")
    public String updatePassengerFirstName(@PathVariable Integer id, @PathVariable String firstName) {
        service.updatePassengerFirstName(id, firstName);
        return "Done";
    }

    @PutMapping("{id}/updateLastName/{lastName}")
    public String updatePassengerLastName(@PathVariable Integer id, @PathVariable String lastName) {
        service.updatePassengerLastName(id, lastName);
        return "Done";
    }
}
