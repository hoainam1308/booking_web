package com.example.booking_web.service;

import com.example.booking_web.entity.Room;
//import org.springframework.web.multipart.MultipartFile;
//import java.io.IOException;
//import java.math.BigDecimal;
//import java.sql.SQLException;
//import java.time.LocalDate;
import java.util.List;
//import java.util.Optional;

public interface IRoomService {
//    Room addNewRoom(MultipartFile photo, String roomType, BigDecimal roomPrice) throws IOException, SQLException;
//    byte[] getRoomPhotoByRoomId(Long roomId) throws SQLException;
//    void deleteRoom(long roomId);
//    Room updateRoom(Long roomId, String roomType, BigDecimal roomPrice, byte[] photoBytes) throws SQLException;
//    Optional<Room> getRoomById(Long roomId);
//    List<Room> getAvailabeRoom(LocalDate checkInDate, LocalDate checkOutDate, String roomType);
    List<Room> getRoomsByHotelsId(Long hotelId);
    void addNewRoom(Room room);
    List<Room> getAllRoomByIds(List<Long> roomIds);
}
