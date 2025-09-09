package com.sahilKumar.house_rental_portal.service;

import com.sahilKumar.house_rental_portal.dto.PropertyRequestDTO;
import com.sahilKumar.house_rental_portal.models.Property;
import java.util.List;
public interface PropertyService {
    Property addProperty(PropertyRequestDTO propertyRequestDTO, String ownerEmail);

    List<Property> getAllProperties();
    List<Property> getAllApprovedProperties();
    Property approveProperty(Long propertyId);
    Property rejectProperty(Long propertyId);
}