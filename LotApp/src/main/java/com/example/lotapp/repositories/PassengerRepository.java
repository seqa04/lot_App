package com.example.lotapp.repositories;

import com.example.lotapp.entities.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Integer> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE PASSENGER SET PHONE_NUMBER = :phoneNumber WHERE ID = :id", nativeQuery = true)
    void updatePassengerPhoneNumber(Integer id, String phoneNumber);

    @Transactional
    @Modifying
    @Query(value = "UPDATE PASSENGER SET FIRST_NAME = :firstName WHERE ID = :id", nativeQuery = true)
    void updatePassengerFirstName(Integer id, String firstName);

    @Transactional
    @Modifying
    @Query(value = "UPDATE PASSENGER SET LAST_NAME = :lastName WHERE ID = :id", nativeQuery = true)
    void updatePassengerLastName(Integer id, String lastName);
}
