package com.sahilKumar.house_rental_portal.service.impl;

import com.sahilKumar.house_rental_portal.dto.BookingRequestDTO;
import com.sahilKumar.house_rental_portal.models.Booking;
import com.sahilKumar.house_rental_portal.models.Property;
import com.sahilKumar.house_rental_portal.models.User;
import com.sahilKumar.house_rental_portal.models.enums.BookingStatus;
import com.sahilKumar.house_rental_portal.models.enums.PropertyType;
import com.sahilKumar.house_rental_portal.repository.BookingRepository;
import com.sahilKumar.house_rental_portal.repository.PropertyRepository;
import com.sahilKumar.house_rental_portal.repository.UserRepository;
import com.sahilKumar.house_rental_portal.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List; // Add this import

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final PropertyRepository propertyRepository;
    private final UserRepository userRepository;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository, PropertyRepository propertyRepository, UserRepository userRepository) {
        this.bookingRepository = bookingRepository;
        this.propertyRepository = propertyRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Booking createBooking(BookingRequestDTO dto, String customerEmail) {
        User customer = userRepository.findByEmail(customerEmail)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Property property = propertyRepository.findById(dto.getPropertyId())
                .orElseThrow(() -> new RuntimeException("Property not found"));

        // Validation checks
        if (property.getType() != PropertyType.RENT) {
            throw new RuntimeException("This property is not for rent");
        }

        // Calculate the total number of days for the booking
        long numberOfDays = ChronoUnit.DAYS.between(dto.getCheckInDate(), dto.getCheckOutDate());
        if (numberOfDays <= 0) {
            throw new RuntimeException("Check-out date must be after check-in date");
        }

        // Create and save the booking
        Booking booking = new Booking();
        booking.setCustomer(customer);
        booking.setProperty(property);
        booking.setCheckInDate(dto.getCheckInDate());
        booking.setCheckOutDate(dto.getCheckOutDate());
        booking.setTotalPrice(property.getPrice() * numberOfDays);
        booking.setBookingStatus(BookingStatus.PENDING); // Pending owner confirmation

        return bookingRepository.save(booking);
    }

    // --- ADD THE NEW METHODS BELOW ---

    @Override
    public List<Booking> getBookingsForOwner(String ownerEmail) {
        User owner = userRepository.findByEmail(ownerEmail)
                .orElseThrow(() -> new RuntimeException("Owner not found"));
        return bookingRepository.findByPropertyOwner(owner);
    }

    @Override
    public Booking confirmBooking(Long bookingId, String ownerEmail) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        // Security Check: Ensure the person confirming is the actual property owner
        if (!booking.getProperty().getOwner().getEmail().equals(ownerEmail)) {
            throw new RuntimeException("You are not authorized to confirm this booking");
        }

        booking.setBookingStatus(BookingStatus.CONFIRMED);
        return bookingRepository.save(booking);
    }

    @Override
    public Booking cancelBooking(Long bookingId, String ownerEmail) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        // Security Check: Ensure the person cancelling is the actual property owner
        if (!booking.getProperty().getOwner().getEmail().equals(ownerEmail)) {
            throw new RuntimeException("You are not authorized to cancel this booking");
        }

        booking.setBookingStatus(BookingStatus.CANCELLED);
        return bookingRepository.save(booking);
    }
    @Override
    public List<Booking> getBookingsForCustomer(String customerEmail) {
        User customer = userRepository.findByEmail(customerEmail)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        return bookingRepository.findByCustomer(customer);
    }
}