package com.example.booking_web.repository;

import com.example.booking_web.entity.Booking;
import com.example.booking_web.entity.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUserId(Long userId); // Tìm đặt phòng theo người dùng
    List<Booking> findByHotelId(Long hotelId); // Tìm đặt phòng theo khách sạn
    List<Booking> findByStatus(BookingStatus status); // Tìm đặt phòng theo trạng thái
    List<Booking> findByCheckInDateBetween(LocalDate startDate, LocalDate endDate); // Tìm đặt phòng trong khoảng thời gian
}