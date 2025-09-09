package com.sahilKumar.house_rental_portal.dto;

import lombok.Data;

@Data
public class SaleInquiryRequestDTO {
    private Long propertyId;
    private double offerPrice;
    private String message;
}