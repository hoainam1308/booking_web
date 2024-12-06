package com.example.booking_web.entity;

import lombok.Data;

import java.util.List;

@Data
public class HotelDTO {
    private String name;         // Tên khách sạn
    private String phoneNumber;  // Số điện thoại
    private String email;        // Email khách sạn
    private String description;  // Mô tả khách sạn
    private Float starRating;    // Số sao
    private String street;       // Địa chỉ chi tiết
    private int wardCode;        // Mã code của Ward
    private List<Long> facilityIds;
}
