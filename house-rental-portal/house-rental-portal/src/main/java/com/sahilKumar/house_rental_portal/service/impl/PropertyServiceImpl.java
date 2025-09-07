package com.sahilKumar.house_rental_portal.service.impl;

import com.sahilKumar.house_rental_portal.dto.PropertyRequestDTO;
import com.sahilKumar.house_rental_portal.models.Location;
import com.sahilKumar.house_rental_portal.models.Property;
import com.sahilKumar.house_rental_portal.models.User;
import com.sahilKumar.house_rental_portal.models.enums.PropertyStatus;
import com.sahilKumar.house_rental_portal.repository.LocationRepository;
import com.sahilKumar.house_rental_portal.repository.PropertyRepository;
import com.sahilKumar.house_rental_portal.repository.UserRepository;
import com.sahilKumar.house_rental_portal.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PropertyServiceImpl implements PropertyService {

    private final PropertyRepository propertyRepository;
    private final UserRepository userRepository;
    private final LocationRepository locationRepository;

    @Autowired
    public PropertyServiceImpl(PropertyRepository propertyRepository, UserRepository userRepository, LocationRepository locationRepository) {
        this.propertyRepository = propertyRepository;
        this.userRepository = userRepository;
        this.locationRepository = locationRepository;
    }

    @Override
    public Property addProperty(PropertyRequestDTO dto, String ownerEmail) {
        User owner = userRepository.findByEmail(ownerEmail)
                .orElseThrow(() -> new RuntimeException("Owner not found"));
        Location location = locationRepository.findById(dto.getLocationId())
                .orElseThrow(() -> new RuntimeException("Location not found"));

        Property property = new Property();
        property.setName(dto.getName());
        property.setDescription(dto.getDescription());
        property.setAddress(dto.getAddress());
        property.setImageUrl(dto.getImageUrl());
        property.setPrice(dto.getPrice());
        property.setType(dto.getType());
        property.setOwner(owner);
        property.setLocation(location);
        property.setStatus(PropertyStatus.PENDING);
        return propertyRepository.save(property);
    }

    @Override
    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    @Override
    public Property approveProperty(Long propertyId) {
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new RuntimeException("Property not found with id: " + propertyId));
        property.setStatus(PropertyStatus.APPROVED);
        return propertyRepository.save(property);
    }

    @Override
    public Property rejectProperty(Long propertyId) {
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new RuntimeException("Property not found with id: " + propertyId));
        property.setStatus(PropertyStatus.REJECTED);
        return propertyRepository.save(property);
    }

    // Add the implementation for the new method
    @Override
    public List<Property> getAllApprovedProperties() {
        return propertyRepository.findByStatus(PropertyStatus.APPROVED);
    }
}