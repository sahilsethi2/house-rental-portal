package com.sahilKumar.house_rental_portal.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data // Lombok: Creates getters, setters, toString(), etc.
@NoArgsConstructor // Lombok: Creates a no-argument constructor
@AllArgsConstructor // Lombok: Creates a constructor with all arguments
@Entity // Tells JPA this class is a table in the database
@Table(name = "locations") // Specifies the table name
public class Location {

    @Id // Marks this field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increments the ID
    private Long id;

    @Column(nullable = false, unique = true) // Column cannot be null and must be unique
    private String name;
}