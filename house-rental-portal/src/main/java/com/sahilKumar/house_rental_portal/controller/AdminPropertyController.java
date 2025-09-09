package com.sahilKumar.house_rental_portal.controller;

import com.sahilKumar.house_rental_portal.models.Property;
import com.sahilKumar.house_rental_portal.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/properties")
public class AdminPropertyController {

    private final PropertyService propertyService;

    @Autowired
    public AdminPropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    // Endpoint for Admin to get all properties (pending, approved, rejected)
    @GetMapping
    public ResponseEntity<List<Property>> getAllProperties() {
        List<Property> properties = propertyService.getAllProperties();
        return ResponseEntity.ok(properties);
    }

    // Endpoint for Admin to approve a property
    // We use @PathVariable to get the ID from the URL
    @PutMapping("/{propertyId}/approve")
    public ResponseEntity<Property> approveProperty(@PathVariable Long propertyId) {
        Property approvedProperty = propertyService.approveProperty(propertyId);
        return ResponseEntity.ok(approvedProperty);
    }

    // Endpoint for Admin to reject a property
    @PutMapping("/{propertyId}/reject")
    public ResponseEntity<Property> rejectProperty(@PathVariable Long propertyId) {
        Property rejectedProperty = propertyService.rejectProperty(propertyId);
        return ResponseEntity.ok(rejectedProperty);
    }
}