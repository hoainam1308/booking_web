package com.example.booking_web.entity;

import com.example.booking_web.service.IRoomService;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
public class BookingDTO {

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private int numberOfGuests;

    private BookingStatus status;

    private Long userId;

    private Long hotelId;

    private List<Long> roomIds;
}
