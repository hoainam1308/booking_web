package com.example.booking_web.service;

import com.example.booking_web.entity.RoomFacility;
import com.example.booking_web.repository.RoomFacilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomFacilityService {
    private final RoomFacilityRepository roomFacilityRepository;

    @Autowired
    public RoomFacilityService(RoomFacilityRepository roomFacilityRepository){
        this.roomFacilityRepository = roomFacilityRepository;
    }

    public RoomFacility findById (Long id){
        return roomFacilityRepository.findById(id).orElse(null);
    }
}
