package com.sahilKumar.house_rental_portal.controller;

import com.sahilKumar.house_rental_portal.dto.SaleInquiryRequestDTO;
import com.sahilKumar.house_rental_portal.models.SaleInquiry;
import com.sahilKumar.house_rental_portal.service.SaleInquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import java.util.List;
import java.security.Principal;

@RestController
@RequestMapping("/api/inquiries")
public class SaleInquiryController {

    private final SaleInquiryService saleInquiryService;

    @Autowired
    public SaleInquiryController(SaleInquiryService saleInquiryService) {
        this.saleInquiryService = saleInquiryService;
    }

    @PostMapping
    public ResponseEntity<SaleInquiry> createInquiry(@RequestBody SaleInquiryRequestDTO inquiryRequestDTO, Principal principal) {
        SaleInquiry newInquiry = saleInquiryService.createInquiry(inquiryRequestDTO, principal.getName());
        return new ResponseEntity<>(newInquiry, HttpStatus.CREATED);
    }
    @GetMapping("/owner")
    public ResponseEntity<List<SaleInquiry>> getInquiriesForOwner(Principal principal) {
        List<SaleInquiry> inquiries = saleInquiryService.getInquiriesForOwner(principal.getName());
        return ResponseEntity.ok(inquiries);
    }

    @PutMapping("/{inquiryId}/accept")
    public ResponseEntity<SaleInquiry> acceptInquiry(@PathVariable Long inquiryId, Principal principal) {
        SaleInquiry acceptedInquiry = saleInquiryService.acceptInquiry(inquiryId, principal.getName());
        return ResponseEntity.ok(acceptedInquiry);
    }

    @PutMapping("/{inquiryId}/reject")
    public ResponseEntity<SaleInquiry> rejectInquiry(@PathVariable Long inquiryId, Principal principal) {
        SaleInquiry rejectedInquiry = saleInquiryService.rejectInquiry(inquiryId, principal.getName());
        return ResponseEntity.ok(rejectedInquiry);
    }
}