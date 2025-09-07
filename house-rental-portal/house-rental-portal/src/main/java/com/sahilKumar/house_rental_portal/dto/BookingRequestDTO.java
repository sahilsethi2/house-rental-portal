package com.sahilKumar.house_rental_portal.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class BookingRequestDTO {
    private Long propertyId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
}