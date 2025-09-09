package com.sahilKumar.house_rental_portal.service.impl;

import com.sahilKumar.house_rental_portal.dto.SaleInquiryRequestDTO;
import com.sahilKumar.house_rental_portal.models.Property;
import com.sahilKumar.house_rental_portal.models.SaleInquiry;
import com.sahilKumar.house_rental_portal.models.User;
import com.sahilKumar.house_rental_portal.models.enums.InquiryStatus;
import com.sahilKumar.house_rental_portal.models.enums.PropertyType;
import com.sahilKumar.house_rental_portal.repository.PropertyRepository;
import com.sahilKumar.house_rental_portal.repository.SaleInquiryRepository;
import com.sahilKumar.house_rental_portal.repository.UserRepository;
import com.sahilKumar.house_rental_portal.service.SaleInquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SaleInquiryServiceImpl implements SaleInquiryService {

    private final SaleInquiryRepository saleInquiryRepository;
    private final PropertyRepository propertyRepository;
    private final UserRepository userRepository;

    @Autowired
    public SaleInquiryServiceImpl(SaleInquiryRepository saleInquiryRepository, PropertyRepository propertyRepository, UserRepository userRepository) {
        this.saleInquiryRepository = saleInquiryRepository;
        this.propertyRepository = propertyRepository;
        this.userRepository = userRepository;
    }

    @Override
    public SaleInquiry createInquiry(SaleInquiryRequestDTO dto, String customerEmail) {
        User customer = userRepository.findByEmail(customerEmail)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Property property = propertyRepository.findById(dto.getPropertyId())
                .orElseThrow(() -> new RuntimeException("Property not found"));

        // Validation: Ensure the property is actually for sale
        if (property.getType() != PropertyType.SALE) {
            throw new RuntimeException("This property is not for sale");
        }

        SaleInquiry inquiry = new SaleInquiry();
        inquiry.setCustomer(customer);
        inquiry.setProperty(property);
        inquiry.setOfferPrice(dto.getOfferPrice());
        inquiry.setMessage(dto.getMessage());
        inquiry.setStatus(InquiryStatus.PENDING); // Owner needs to review the offer

        return saleInquiryRepository.save(inquiry);
    }
    @Override
    public List<SaleInquiry> getInquiriesForOwner(String ownerEmail) {
        User owner = userRepository.findByEmail(ownerEmail)
                .orElseThrow(() -> new RuntimeException("Owner not found"));
        return saleInquiryRepository.findByPropertyOwner(owner);
    }

    @Override
    public SaleInquiry acceptInquiry(Long inquiryId, String ownerEmail) {
        SaleInquiry inquiry = saleInquiryRepository.findById(inquiryId)
                .orElseThrow(() -> new RuntimeException("Inquiry not found"));

        // Security Check: Ensure the person acting is the actual property owner
        if (!inquiry.getProperty().getOwner().getEmail().equals(ownerEmail)) {
            throw new RuntimeException("You are not authorized to modify this inquiry");
        }

        inquiry.setStatus(InquiryStatus.ACCEPTED);
        return saleInquiryRepository.save(inquiry);
    }

    @Override
    public SaleInquiry rejectInquiry(Long inquiryId, String ownerEmail) {
        SaleInquiry inquiry = saleInquiryRepository.findById(inquiryId)
                .orElseThrow(() -> new RuntimeException("Inquiry not found"));

        // Security Check
        if (!inquiry.getProperty().getOwner().getEmail().equals(ownerEmail)) {
            throw new RuntimeException("You are not authorized to modify this inquiry");
        }

        inquiry.setStatus(InquiryStatus.REJECTED);
        return saleInquiryRepository.save(inquiry);
    }
}