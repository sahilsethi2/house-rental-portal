package com.sahilKumar.house_rental_portal.service;

import com.sahilKumar.house_rental_portal.models.Location;
import com.sahilKumar.house_rental_portal.repository.LocationRepository;
import com.sahilKumar.house_rental_portal.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    @Autowired
    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public Location addLocation(String locationName) {
        // Create a new Location object
        Location location = new Location();
        location.setName(locationName);

        // Save it to the database using the repository
        return locationRepository.save(location);
    }

    @Override
    public List<Location> getAllLocations() {
        // Retrieve all locations from the database
        return locationRepository.findAll();
    }
}