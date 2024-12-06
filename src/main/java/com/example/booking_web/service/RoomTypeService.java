package com.example.booking_web.service;

import com.example.booking_web.entity.RoomType;
import com.example.booking_web.repository.RoomTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomTypeService implements IRoomTypeService{
    private final RoomTypeRepository roomTypeRepository;

    @Autowired
    public RoomTypeService(RoomTypeRepository roomTypeRepository) {
        this.roomTypeRepository = roomTypeRepository;
    }

    public List<RoomType> findAllRoomType() {
        return roomTypeRepository.findAll();
    }

    public RoomType findRoomTypeById(Long id) {
        return roomTypeRepository.findById(id).orElse(null);
    }

    public void addNewRoomType(RoomType roomType) {
        roomTypeRepository.save(roomType);
    }

}
