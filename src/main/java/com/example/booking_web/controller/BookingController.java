package com.example.booking_web.controller;

import com.example.booking_web.entity.*;
import com.example.booking_web.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final IBookingService bookingService;
    private final UserService userService;
    private final IHotelService hotelService;
    private final IRoomService roomService;

    public BookingController(IBookingService bookingService, UserService userService, IHotelService hotelService, IRoomService roomService) {
        this.bookingService = bookingService;
        this.userService = userService;
        this.hotelService = hotelService;
        this.roomService = roomService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addBooking(@RequestBody BookingDTO bookingDTO) {
        try {
            // Tạo đối tượng Booking từ BookingDTO
            Booking booking = new Booking();
            booking.setCheckInDate(bookingDTO.getCheckInDate());
            booking.setCheckOutDate(bookingDTO.getCheckOutDate());
            booking.setNumberOfGuests(bookingDTO.getNumberOfGuests());
            booking.setStatus(bookingDTO.getStatus());

            // Lấy đối tượng User từ userId trong DTO
            User user = userService.getUserById(bookingDTO.getUserId());
            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
            }
            booking.setUser(user);

            // Lấy đối tượng Hotel từ hotelId trong DTO
            Hotel hotel = hotelService.getHotelById(bookingDTO.getHotelId());
            if (hotel == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Hotel not found.");
            }
            booking.setHotel(hotel);

            // Lấy danh sách Room từ roomIds trong DTO
            List<Room> rooms = roomService.getAllRoomByIds(bookingDTO.getRoomIds());
            if (rooms.size() != bookingDTO.getRoomIds().size()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Some rooms not found.");
            }
            booking.setRooms(rooms);

            // Tính tổng giá dựa trên các phòng (giả sử mỗi phòng có giá)
            BigDecimal totalPrice = rooms.stream()
                    .map(Room::getRoomPrice) // Lấy giá phòng (BigDecimal)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            booking.setTotalPrice(totalPrice);

            // Lưu Booking vào cơ sở dữ liệu
            bookingService.saveBooking(booking);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Booking created successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Booking>> getBookingsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(bookingService.findBookingsByUser(userId));
    }

    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<Booking>> getBookingsByHotel(@PathVariable Long hotelId) {
        return ResponseEntity.ok(bookingService.findBookingsByHotel(hotelId));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Booking>> getBookingsByStatus(@PathVariable BookingStatus status) {
        return ResponseEntity.ok(bookingService.findBookingsByStatus(status));
    }
}
