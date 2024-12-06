package com.example.booking_web.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class RoomDTO {
    private String name;
    private BigDecimal roomPrice;
    private int quantity;
    private long hotelId;
    private long roomTypeId;
    private List<Long> facilityIds;
//    private boolean isValid;
}
