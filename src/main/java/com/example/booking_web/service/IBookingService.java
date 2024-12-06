package com.example.booking_web.service;

import com.example.booking_web.entity.Booking;
import com.example.booking_web.entity.BookingStatus;

import java.time.LocalDate;
import java.util.List;

public interface IBookingService {

    void saveBooking(Booking booking);

    List<Booking> findBookingsByUser(Long userId);

    List<Booking> findBookingsByHotel(Long hotelId);

    List<Booking> findBookingsByStatus(BookingStatus status);

    List<Booking> findBookingsBetweenDates(LocalDate startDate, LocalDate endDate);
}
