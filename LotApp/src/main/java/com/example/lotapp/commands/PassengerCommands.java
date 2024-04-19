package com.example.lotapp.commands;

import com.example.lotapp.DTO.PassengerDTO;
import com.example.lotapp.entities.Passenger;
import com.example.lotapp.services.PassengerService;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;

@ShellComponent
@ShellCommandGroup("Passenger Commands")
public class PassengerCommands {

    private final PassengerService pasService;

    public PassengerCommands(PassengerService pasService) {
        this.pasService = pasService;
    }

    @ShellMethod(key = "getAllPassengers",
            value = "Get all passengers\n")
    public List<String> getAllPassengers() {
        return pasService.findAllPassengers().stream().map(Passenger::toString).toList();
    }

    @ShellMethod(key = "getPassengerById",
            value = "Get passenger by id \nexample: getPassengerById 2\n")
    public String findPassengerById(int id) {
        return pasService.findPassengerById(id).toString();
    }

    @ShellMethod(key = "deletePassengerById",
            value = "Delete passenger by id \nexample: deletePassengerById 2\n")
    public String deletePassengerById(int id) {
        pasService.deletePassengerById(id);
        return "Done";
    }

    @ShellMethod(key = "addPassenger",
            value = "Add passenger \nexample: addPassenger firstName telephoneNumber\n")
    public String addPassenger(String firstName, String lastName, String phoneNumber) {
        return pasService.addPassenger(new PassengerDTO(firstName, lastName, phoneNumber)).toString();
    }

    @ShellMethod(key = "updatePhoneNumber",
            value = "Update phone number of passenger with given id \nexample: updatePhoneNumber 1 123456789\n")
    public String updatePassengerPhoneNumber(int id, String phoneNumber) {
        pasService.updatePassengerPhoneNumber(id, phoneNumber);
        return "Done";
    }

    @ShellMethod(key = "updateFirstName",
            value = "Update first name of passenger with given id \nexample: updateFirstName 1 Adam\n")
    public String updatePassengerFirstName(int id, String firstName) {
        pasService.updatePassengerFirstName(id, firstName);
        return "Done";
    }

    @ShellMethod(key = "updateLastName",
            value = "Update last name of passenger with given id \nexample: updateLastName 1 Nowak\n")
    public String updatePassengerLastName(int id, String lastName) {
        pasService.updatePassengerLastName(id, lastName);
        return "Done";
    }

    @ShellMethod(key = "addPassengerToFlight",
            value = "Adds passenger to flight \nexample: addPassengerToFlight passengerId flightNumber\n")
    public String addPassengerToFlight(int id, String flightNumber) {
        return pasService.addPassengerToFlight(id, flightNumber).toString();
    }

}
