package com.example.lotapp.services;

import com.example.lotapp.DTO.PassengerDTO;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PassengerValidator {

    public void validate(PassengerDTO dto) {

        validateFirsName(dto.firstName());

        validateLastName(dto.lastName());

        validatePhoneNumber(dto.phoneNumber());
    }

    public void validateFirsName(String firstName) {
        if (firstName == null || firstName.isBlank()) {
            throw new IllegalArgumentException("First name must not be empty");
        }

        Pattern pattern = Pattern.compile("^[a-zA-Z]+$");
        Matcher matcher = pattern.matcher(firstName);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("First name must contain only big and small letters");
        }
    }

    public void validateLastName(String lastName) {
        if (lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException("First name must not be empty");
        }

        Pattern pattern = Pattern.compile("^[a-zA-Z]+$");
        Matcher matcher = pattern.matcher(lastName);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Last name must contain only big and small letters");
        }
    }

    public void validatePhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isBlank()) {
            throw new IllegalArgumentException("Phone Number must not be empty");
        }

        Pattern pattern = Pattern.compile("^[0-9]{9}$");
        Matcher matcher = pattern.matcher(phoneNumber);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Phone Number must have nine digits example 123456789");
        }
    }
}
