package com.example.booking_web.service;

import com.example.booking_web.entity.HotelFacility;
import com.example.booking_web.repository.HotelFacilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelFacilityService {
    private final HotelFacilityRepository hotelFacilityRepository;

    @Autowired
    public HotelFacilityService(HotelFacilityRepository hotelFacilityRepository) {
        this.hotelFacilityRepository = hotelFacilityRepository;
    }

    public HotelFacility findById(Long id) {
        return hotelFacilityRepository.findById(id).orElse(null);
    }
}
