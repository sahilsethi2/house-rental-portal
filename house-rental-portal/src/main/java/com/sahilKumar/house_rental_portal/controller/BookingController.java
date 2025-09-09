package com.sahilKumar.house_rental_portal.controller;

import com.sahilKumar.house_rental_portal.dto.BookingRequestDTO;
import com.sahilKumar.house_rental_portal.models.Booking;
import com.sahilKumar.house_rental_portal.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import java.util.List;
@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestBody BookingRequestDTO bookingRequestDTO, Principal principal) {
        String customerEmail = principal.getName();
        Booking newBooking = bookingService.createBooking(bookingRequestDTO, customerEmail);
        return new ResponseEntity<>(newBooking, HttpStatus.CREATED);
    }
    @GetMapping("/owner")
    public ResponseEntity<List<Booking>> getBookingsForOwner(Principal principal) {
        List<Booking> bookings = bookingService.getBookingsForOwner(principal.getName());
        return ResponseEntity.ok(bookings);
    }

    @PutMapping("/{bookingId}/confirm")
    public ResponseEntity<Booking> confirmBooking(@PathVariable Long bookingId, Principal principal) {
        Booking confirmedBooking = bookingService.confirmBooking(bookingId, principal.getName());
        return ResponseEntity.ok(confirmedBooking);
    }

    @PutMapping("/{bookingId}/cancel")
    public ResponseEntity<Booking> cancelBooking(@PathVariable Long bookingId, Principal principal) {
        Booking cancelledBooking = bookingService.cancelBooking(bookingId, principal.getName());
        return ResponseEntity.ok(cancelledBooking);
    }

    @GetMapping("/my-bookings")
    public ResponseEntity<List<Booking>> getBookingsForCustomer(Principal principal) {
        List<Booking> bookings = bookingService.getBookingsForCustomer(principal.getName());
        return ResponseEntity.ok(bookings);
    }
}