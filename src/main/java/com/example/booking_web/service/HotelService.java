package com.example.booking_web.service;

import com.example.booking_web.entity.District;
import com.example.booking_web.entity.Hotel;
import com.example.booking_web.entity.Province;
import com.example.booking_web.entity.Ward;
import com.example.booking_web.repository.HotelRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService implements IHotelService{
    private final HotelRepository hotelRepository;

    @Autowired
    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    // Tìm khách sạn theo xã/phường
    public List<Hotel> findHotelsByWard(Long wardId) {
        return hotelRepository.findByWardId(wardId);
    }

    // Tìm khách sạn theo quận/huyện
    public List<Hotel> findHotelsByDistrict(Long districtId) {
        return hotelRepository.findByDistrictId(districtId);
    }

    // Tìm khách sạn theo tỉnh/thành
    public List<Hotel> findHotelsByProvince(Long provinceId) {
        return hotelRepository.findByProvinceId(provinceId);
    }

    public void addNewHotel(Hotel hotel){
        hotelRepository.save(hotel);
    }

    public String getAddressByHotelId(Long hotelId) {
        Hotel hotel = hotelRepository.findHotelWithAddress(hotelId);
        if (hotel == null) {
            throw new EntityNotFoundException("Hotel not found with ID: " + hotelId);
        }

        // Lấy thông tin địa chỉ
        Ward ward = hotel.getWard();
        District district = ward.getDistrict();
        Province province = district.getProvince();

        return String.format(
                "%s, %s, %s, %s",
                hotel.getStreet(),
                ward.getName(),
                district.getName(),
                province.getName()
        );
    }

    public Hotel getHotelById(Long hotelId) {
        return hotelRepository.findById(hotelId).orElse(null);
    }

}
