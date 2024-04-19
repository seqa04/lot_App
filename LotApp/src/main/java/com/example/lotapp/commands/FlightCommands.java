package com.example.lotapp.commands;

import com.example.lotapp.DTO.FindingFlightDTO;
import com.example.lotapp.DTO.FlightDTO;
import com.example.lotapp.entities.Flight;
import com.example.lotapp.services.FlightService;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.List;

@ShellComponent
@ShellCommandGroup("Flight Commands")
public class FlightCommands {

    private final FlightService service;

    public FlightCommands(FlightService service) {
        this.service = service;
    }

    @ShellMethod(key = "getAllFlights",
            value = "Print all flights")
    public List<String> getAllFlights() {
        return service.findAllFlights().stream().map(Flight::toString).toList();
    }

    @ShellMethod(key = "getFlightByFlightNumber",
            value = "Print flight with provided flight number \nexample: getFlightByFlightNumber LO1234\n")
    public String findByFlightNumber(String flightNumber) {
        return service.findByFlightNumber(flightNumber).toString();
    }

    @ShellMethod(key = "findFlights",
            value = "Find flight based on some optional criteria \nexample: deleteFlightByFlightNumber --freeSeats --from 2022-12-01_10:00:00 --to 2022-12-01_10:00:00 --departureAirport Krakow" +
                    " --destinationAirport Warsaw\n if freeSeats options provided looking only for flights with free seats\n" +
                    "none of the options are mandatory\n")
    public List<String> findFlights(boolean freeSeats,
                                    @ShellOption(defaultValue = ShellOption.NULL) String from,
                                    @ShellOption(defaultValue = ShellOption.NULL) String to,
                                    @ShellOption(defaultValue = ShellOption.NULL) String departureAirport,
                                    @ShellOption(defaultValue = ShellOption.NULL) String destinationAirport) {
        FindingFlightDTO dto = new FindingFlightDTO(from,
                to,
                freeSeats, departureAirport, destinationAirport);


        return service.findFlights(dto).stream().map(Flight::toString).toList();
    }

    @ShellMethod(key = "deleteFlightByFlightNumber",
            value = "Delete flight with provided flight number \nexample: deleteFlightByFlightNumber LO1234\n")
    public String deleteFlightByFlightNumber(String flightNumber) {
        service.deleteFlightByFlightNumber(flightNumber);
        return "Done";
    }

    @ShellMethod(key = "updateDepartureAirport",
            value = "Update departure airport of flight with provided flight number \nexample: updateDepartureAirport LO1234 Warsaw\n")
    public String updateFlightDepartureAirport(String flightNumber, String departureAirport) {
        service.updateFlightDepartureAirport(flightNumber, departureAirport);
        return "Done";
    }

    @ShellMethod(key = "updateDestinationAirport",
            value = "Update destination airport of flight with provided flight number \nexample: updateDestinationAirport LO1234 Warsaw\n")
    public String updateFlightDestinationAirport(String flightNumber, String destinationAirport) {
        service.updateFlightDestinationAirport(flightNumber, destinationAirport);
        return "Done";
    }

    @ShellMethod(key = "updateDepartureDate",
            value = "Update departure date of flight with provided flight number date must have format yyyy-MM-dd_HH:mm:ss \nexample: updateDepartureDate 2025-12-01_10:00:00\n")
    public String updateFlightDepartureDate(String flightNumber, String departureDate) {
        service.updateFlightDepartureDate(flightNumber, departureDate);
        return "Done";
    }

    @ShellMethod(key = "addFlight",
            value = "Adds new flight date must have format yyyy-MM-dd_HH:mm:ss \nexample: addFlight flightNumber departureAirport destinationAirport departureDate numberOfSeats\n")
    public String addFlight(String flightNumber, String departureAirport,
                            String destinationAirport, String departureDate,
                            Integer numberOfFreeSeats) {

        FlightDTO dto = new FlightDTO(flightNumber, departureAirport, destinationAirport,
                departureDate, numberOfFreeSeats);
        return service.addFlight(dto).toString();
    }

}
