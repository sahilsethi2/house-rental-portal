package com.sahilKumar.house_rental_portal.controller;

import com.sahilKumar.house_rental_portal.dto.LocationDTO;
import com.sahilKumar.house_rental_portal.models.Location;
import com.sahilKumar.house_rental_portal.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/locations") // Base path for admin location management
public class LocationController {

    private final LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    // Endpoint to add a new location
    @PostMapping
    public ResponseEntity<Location> addLocation(@RequestBody LocationDTO locationDTO) {
        Location newLocation = locationService.addLocation(locationDTO.getName());
        return new ResponseEntity<>(newLocation, HttpStatus.CREATED);
    }

    // Endpoint to get all locations
    @GetMapping
    public ResponseEntity<List<Location>> getAllLocations() {
        List<Location> locations = locationService.getAllLocations();
        return new ResponseEntity<>(locations, HttpStatus.OK);
    }
}