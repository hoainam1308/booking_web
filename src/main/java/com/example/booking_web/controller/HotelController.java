package com.example.booking_web.controller;

import com.example.booking_web.entity.Hotel;
import com.example.booking_web.entity.HotelDTO;
import com.example.booking_web.entity.HotelFacility;
import com.example.booking_web.entity.Ward;
import com.example.booking_web.service.HotelFacilityService;
import com.example.booking_web.service.IHotelService;
import com.example.booking_web.service.IWardService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {
    private final IHotelService hotelService;
    private final IWardService wardService;
    private final HotelFacilityService hotelFacilityService;
    @Autowired
    public HotelController(IHotelService hotelService, IWardService wardService, HotelFacilityService hotelFacilityService) {
        this.hotelService = hotelService;
        this.wardService = wardService;
        this.hotelFacilityService = hotelFacilityService;
    }

    @GetMapping("/by-ward/{wardId}")
    public List<Hotel> getHotelsByWard(@PathVariable Long wardId) {
        return hotelService.findHotelsByWard(wardId);
    }

    @GetMapping("/by-district/{districtId}")
    public List<Hotel> getHotelsByDistrict(@PathVariable Long districtId) {
        return hotelService.findHotelsByDistrict(districtId);
    }

    @GetMapping("/by-province/{provinceId}")
    public List<Hotel> getHotelsByProvince(@PathVariable Long provinceId) {
        return hotelService.findHotelsByProvince(provinceId);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addHotel(@RequestBody HotelDTO hotelDTO) {
        try {
            // Kiểm tra Ward (Xã/Phường) có tồn tại không
            Ward ward = wardService.findByCode(hotelDTO.getWardCode());
            if (ward == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Ward with code " + hotelDTO.getWardCode() + " not found.");
            }

            // Chuyển đổi HotelDTO sang Hotel entity
            Hotel hotel = new Hotel();
            hotel.setName(hotelDTO.getName());
            hotel.setPhoneNumber(hotelDTO.getPhoneNumber());
            hotel.setEmail(hotelDTO.getEmail());
            hotel.setDescription(hotelDTO.getDescription());
            hotel.setStarRating(hotelDTO.getStarRating());
            hotel.setWard(ward);
            hotel.setStreet(hotelDTO.getStreet());

            List<HotelFacility> facilities = new ArrayList<>();
            if (hotelDTO.getFacilityIds() != null && !hotelDTO.getFacilityIds().isEmpty()) {
                for (Long facilityId : hotelDTO.getFacilityIds()) {
                    HotelFacility facility = hotelFacilityService.findById(facilityId);
                    if (facility != null) {
                        facilities.add(facility);
                    }
                }
            }
            hotel.setFacilities(facilities);

            // Lưu khách sạn vào cơ sở dữ liệu
            hotelService.addNewHotel(hotel);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Hotel added successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error occurred while adding hotel: " + e.getMessage());
        }
    }

    @GetMapping("/address/{hotelId}")
    public ResponseEntity<String> getHotelAddress(@PathVariable Long hotelId) {
        try {
            String address = hotelService.getAddressByHotelId(hotelId);
            return ResponseEntity.ok(address);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while fetching the address.");
        }
    }
}
