package com.sahilKumar.house_rental_portal.dto;

import com.sahilKumar.house_rental_portal.models.enums.PropertyType;
import lombok.Data;

@Data
public class PropertyRequestDTO {
    private String name;
    private String description;
    private String address;
    private String imageUrl;
    private double price;
    private PropertyType type; // SALE, RENT, or PG
    private Long locationId;
}