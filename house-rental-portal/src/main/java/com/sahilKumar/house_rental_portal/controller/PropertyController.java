package com.sahilKumar.house_rental_portal.controller;

import com.sahilKumar.house_rental_portal.dto.PropertyRequestDTO;
import com.sahilKumar.house_rental_portal.models.Property;
import com.sahilKumar.house_rental_portal.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {

    private final PropertyService propertyService;

    @Autowired
    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    // This endpoint remains for property owners to add properties
    @PostMapping
    public ResponseEntity<Property> addProperty(@RequestBody PropertyRequestDTO propertyRequestDTO, Principal principal) {
        String ownerEmail = principal.getName();
        Property newProperty = propertyService.addProperty(propertyRequestDTO, ownerEmail);
        return new ResponseEntity<>(newProperty, HttpStatus.CREATED);
    }

    // Add this new endpoint for the public to view approved properties
    @GetMapping
    public ResponseEntity<List<Property>> getAllApprovedProperties() {
        List<Property> properties = propertyService.getAllApprovedProperties();
        return ResponseEntity.ok(properties);
    }
}