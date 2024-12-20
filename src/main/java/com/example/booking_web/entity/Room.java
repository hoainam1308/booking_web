package com.example.booking_web.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal roomPrice;
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name = "roomType_id")
    private RoomType roomType;

    @ManyToMany
    @JoinTable(
            name = "room_facility_mapping", // Tên bảng trung gian
            joinColumns = @JoinColumn(name = "room_id"), // Khóa ngoại trỏ đến bảng Room
            inverseJoinColumns = @JoinColumn(name = "room_facility_id") // Khóa ngoại trỏ đến bảng RoomFacility
    )
    @JsonIgnore
    private List<RoomFacility> facilities = new ArrayList<>();
}
