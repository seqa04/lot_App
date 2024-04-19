package com.example.lotapp.endpoints;

import com.example.lotapp.DTO.FindingFlightDTO;
import com.example.lotapp.DTO.FlightDTO;
import com.example.lotapp.entities.Flight;
import com.example.lotapp.services.FlightService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("flight")
public class FlightEndpoint {

    private final FlightService service;

    public FlightEndpoint(FlightService service) {
        this.service = service;
    }

    @PostMapping("/add")
    Flight add(@RequestBody FlightDTO flight) {
        return service.addFlight(flight);
    }

    @GetMapping("/all")
    public List<Flight> findAllFlightsById() {
        return service.findAllFlights();
    }

    @GetMapping("/{flightNumber}")
    Flight findFlightByFlightNumber(@PathVariable String flightNumber) {
        return service.findByFlightNumber(flightNumber);
    }

    @GetMapping("find")
    List<Flight> findFlightByFlightNumber(@RequestBody FindingFlightDTO findingFlightDTO) {
        return service.findFlights(findingFlightDTO);
    }

    @DeleteMapping("/{flightNumber}")
    String deleteFlightByFlightNumber(@PathVariable String flightNumber) {
        service.deleteFlightByFlightNumber(flightNumber);
        return "Done";
    }

    @PutMapping("{flightNumber}/updateDepartureAirport/{departureAirport}")
    public String updateFlightDepartureAirport(@PathVariable String flightNumber, @PathVariable String departureAirport) {
        service.updateFlightDepartureAirport(flightNumber, departureAirport);
        return "Done";
    }

    @PutMapping("{flightNumber}/updateDestinationAirport/{destinationAirport}")
    public String updateFlightDestinationAirport(@PathVariable String flightNumber, @PathVariable String destinationAirport) {
        service.updateFlightDestinationAirport(flightNumber, destinationAirport);
        return "Done";
    }

    @PutMapping("{flightNumber}/updateDepartureDate/{departureDate}")
    public String updateFlightDepartureDate(@PathVariable String flightNumber, @PathVariable String departureDate) {
        service.updateFlightDepartureDate(flightNumber, departureDate);
        return "Done";
    }

}
