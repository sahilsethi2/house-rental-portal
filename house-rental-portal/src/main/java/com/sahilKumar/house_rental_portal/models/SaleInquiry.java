package com.sahilKumar.house_rental_portal.models;

import com.sahilKumar.house_rental_portal.models.enums.InquiryStatus;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "sale_inquiries")
public class SaleInquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double offerPrice;

    @Column(length = 1000)
    private String message;

    @Enumerated(EnumType.STRING)
    private InquiryStatus status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", nullable = false)
    private User customer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "property_id", nullable = false)
    private Property property;
}