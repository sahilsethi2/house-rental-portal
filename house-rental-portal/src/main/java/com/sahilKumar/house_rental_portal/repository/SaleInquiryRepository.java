package com.sahilKumar.house_rental_portal.repository;

import com.sahilKumar.house_rental_portal.models.SaleInquiry;
import com.sahilKumar.house_rental_portal.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleInquiryRepository extends JpaRepository<SaleInquiry, Long> {

    // Custom query to find all inquiries where the inquiry's property is owned by the given user
    @Query("SELECT i FROM SaleInquiry i WHERE i.property.owner = :owner")
    List<SaleInquiry> findByPropertyOwner(User owner);
}