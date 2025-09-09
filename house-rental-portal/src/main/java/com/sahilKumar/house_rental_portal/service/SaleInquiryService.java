package com.sahilKumar.house_rental_portal.service;

import com.sahilKumar.house_rental_portal.dto.SaleInquiryRequestDTO;
import com.sahilKumar.house_rental_portal.models.SaleInquiry;

import java.util.List;

public interface SaleInquiryService {
    SaleInquiry createInquiry(SaleInquiryRequestDTO inquiryRequestDTO, String customerEmail);

    // --- Add these new methods for the owner ---
    List<SaleInquiry> getInquiriesForOwner(String ownerEmail);
    SaleInquiry acceptInquiry(Long inquiryId, String ownerEmail);
    SaleInquiry rejectInquiry(Long inquiryId, String ownerEmail);
}