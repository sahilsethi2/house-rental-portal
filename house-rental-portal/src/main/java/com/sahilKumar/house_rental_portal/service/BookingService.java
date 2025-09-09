package com.sahilKumar.house_rental_portal.service;

import com.sahilKumar.house_rental_portal.dto.BookingRequestDTO;
import com.sahilKumar.house_rental_portal.models.Booking;

import java.util.List;

public interface BookingService {
    Booking createBooking(BookingRequestDTO bookingRequestDTO, String customerEmail);

    // --- Add these new methods for the owner ---
    List<Booking> getBookingsForOwner(String ownerEmail);
    Booking confirmBooking(Long bookingId, String ownerEmail);
    Booking cancelBooking(Long bookingId, String ownerEmail);
    List<Booking> getBookingsForCustomer(String customerEmail);
}