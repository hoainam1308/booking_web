package com.example.booking_web.service;



import com.example.booking_web.entity.RoomType;

import java.util.List;
import java.util.Optional;


public interface IRoomTypeService {

    public List<RoomType> findAllRoomType();

    public RoomType findRoomTypeById(Long roomTypeId);

    public void addNewRoomType(RoomType roomType);
}
