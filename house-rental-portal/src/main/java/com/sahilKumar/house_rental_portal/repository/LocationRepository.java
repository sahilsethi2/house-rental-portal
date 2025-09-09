package com.sahilKumar.house_rental_portal.repository;

import com.sahilKumar.house_rental_portal.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Marks this interface as a Spring Data repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    // JpaRepository<Location, Long> means this repository is for the 'Location' entity,
    // and its primary key is of type 'Long'.

    // All basic CRUD (Create, Read, Update, Delete) methods are automatically available.
}