package com.example.booking_web.controller;

import com.example.booking_web.entity.RoomType;
import com.example.booking_web.service.IRoomTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/room/type")
public class RoomTypeController {
    private final IRoomTypeService roomTypeService;
    public RoomTypeController(IRoomTypeService roomTypeService){
        this.roomTypeService = roomTypeService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addRoomType(@RequestBody RoomType roomType) {
        try {
            RoomType newRoomType = new RoomType();
            newRoomType.setName(roomType.getName());
            roomTypeService.addNewRoomType(newRoomType);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Hotel room type successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error occurred while adding room type: " + e.getMessage());
        }
    }


}
