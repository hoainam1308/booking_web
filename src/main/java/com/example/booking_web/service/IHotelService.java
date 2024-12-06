package com.example.booking_web.service;

import com.example.booking_web.entity.Hotel;

import java.util.List;

public interface IHotelService {
    // Tìm khách sạn theo xã/phường
    public List<Hotel> findHotelsByWard(Long wardId);

    // Tìm khách sạn theo quận/huyện
    public List<Hotel> findHotelsByDistrict(Long districtId);

    // Tìm khách sạn theo tỉnh/thành
    public List<Hotel> findHotelsByProvince(Long provinceId);

    public void addNewHotel(Hotel hotel);

    public String getAddressByHotelId(Long hotelId);

    public Hotel getHotelById(Long hotelId);
}
