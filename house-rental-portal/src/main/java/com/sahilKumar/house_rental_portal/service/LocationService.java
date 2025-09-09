package com.sahilKumar.house_rental_portal.service;

import com.sahilKumar.house_rental_portal.models.Location;
import java.util.List;

public interface LocationService {
    Location addLocation(String locationName);
    List<Location> getAllLocations();
}