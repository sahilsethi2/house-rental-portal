package com.sahilKumar.house_rental_portal.repository;

import com.sahilKumar.house_rental_portal.models.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.sahilKumar.house_rental_portal.models.enums.PropertyStatus; // Add this import

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
    // We can add custom query methods here later, for example, to find properties
    // by location, by type, or within a certain price range.
    // For now, the default methods are enough.
    List<Property> findByStatus(PropertyStatus status);
}