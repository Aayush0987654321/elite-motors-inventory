package com.example.demo.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Repository.CarRepository;

import java.util.List;

@RestController
@RequestMapping("/api/inquiries")
public class InquiryController {

    @Autowired
    private InquiryRepository inquiryRepository;

    @Autowired
    private CarRepository CarRepository;

    @PostMapping
    public Inquiry createInquiry(@RequestBody Inquiry inquiry) {
        // Business Logic: Check car price for priority
        car car = CarRepository.findById(inquiry.getCarId()).orElse(null);
        
        if (car != null && car.getPrice() > 100000) {
            inquiry.setPriority("High Priority 🔥");
        } else {
            inquiry.setPriority("Normal");
        }
        
        return inquiryRepository.save(inquiry);
    }

    @GetMapping
    public List<Inquiry> getAllInquiries() {
        return inquiryRepository.findAll();
    }
}