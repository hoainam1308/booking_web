package com.example.booking_web.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    private String name;

    private String phoneNumber; // Số điện thoại liên hệ

    private String email; // Email của khách sạn

    private String description; // Mô tả khách sạn

    private int ratingCount;

    private float starRating; // Số sao trung bình (1-5)

    private String street; // Địa chỉ chi tiết: số nhà, tên đường

    @ManyToOne
    @JoinColumn(name = "ward_id")
    private Ward ward;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Room> rooms; // Danh sách phòng trong khách sạn

    @ManyToMany
    @JoinTable(
            name = "hotel_facility_mapping", // Tên bảng trung gian
            joinColumns = @JoinColumn(name = "hotel_id"), // Khóa ngoại trỏ đến bảng Hotel
            inverseJoinColumns = @JoinColumn(name = "facility_id") // Khóa ngoại trỏ đến bảng HotelFacility
    )
    @JsonIgnore
    private List<HotelFacility> facilities = new ArrayList<>();
}
