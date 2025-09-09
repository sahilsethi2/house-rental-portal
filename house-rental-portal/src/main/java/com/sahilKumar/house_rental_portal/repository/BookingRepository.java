package com.sahilKumar.house_rental_portal.repository;

import com.sahilKumar.house_rental_portal.models.Booking;
import com.sahilKumar.house_rental_portal.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    // Custom query to find all bookings where the booking's property is owned by the given user
    @Query("SELECT b FROM Booking b WHERE b.property.owner = :owner")
    List<Booking> findByCustomer(User customer);
    List<Booking> findByPropertyOwner(User owner);
}