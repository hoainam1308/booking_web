package com.example.booking_web.controller;

import com.example.booking_web.entity.*;
import com.example.booking_web.service.IHotelService;
import com.example.booking_web.service.IRoomService;
import com.example.booking_web.service.IRoomTypeService;
import com.example.booking_web.service.RoomFacilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rooms")
public class RoomController {
    private final IRoomService roomService;
    private final IRoomTypeService roomTypeService;
    private final IHotelService hotelService;
    private final RoomFacilityService roomFacilityService;
    @GetMapping("/hotel/{hotelId}")
    public List<Room> getRoomsByHotel(@PathVariable Long hotelId){
        return roomService.getRoomsByHotelsId(hotelId);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addRoom(@RequestBody RoomDTO roomDTO) {
        try {
            RoomType roomType = roomTypeService.findRoomTypeById(roomDTO.getRoomTypeId());
            if (roomType == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Room type with code " + roomDTO.getRoomTypeId() + " not found.");
            }
            Hotel hotel = hotelService.getHotelById(roomDTO.getHotelId());
            if (hotel == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Hotel with code " + roomDTO.getHotelId() + " not found.");
            }

            Room room = new Room();
            room.setName(roomDTO.getName());
            room.setRoomPrice(roomDTO.getRoomPrice());
            room.setQuantity(roomDTO.getQuantity());
            room.setHotel(hotel);
            room.setRoomType(roomType);

            List<RoomFacility> facilities = new ArrayList<>();
            if (roomDTO.getFacilityIds() != null && !roomDTO.getFacilityIds().isEmpty()) {
                for (Long facilityId : roomDTO.getFacilityIds()) {
                    RoomFacility facility = roomFacilityService.findById(facilityId);
                    if (facility != null) {
                        facilities.add(facility);
                    }
                }
            }
            room.setFacilities(facilities);

            // Lưu room vào cơ sở dữ liệu
            roomService.addNewRoom(room);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Room added successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error occurred while adding room: " + e.getMessage());
        }
    }
}
