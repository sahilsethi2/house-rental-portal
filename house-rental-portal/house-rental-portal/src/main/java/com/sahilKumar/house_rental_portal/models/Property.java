package com.sahilKumar.house_rental_portal.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sahilKumar.house_rental_portal.models.enums.PropertyStatus;
import com.sahilKumar.house_rental_portal.models.enums.PropertyType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "properties")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(length = 1000)
    private String description;

    private String address;
    private String imageUrl;
    private double price;

    @Enumerated(EnumType.STRING)
    private PropertyType type;

    @Enumerated(EnumType.STRING)
    private PropertyStatus status;

    // Change FetchType to EAGER
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id", nullable = false)
    @JsonBackReference
    private User owner;

    // Change FetchType to EAGER
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;
}