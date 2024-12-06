package com.example.booking_web.repository;

import com.example.booking_web.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
    // Tìm khách sạn theo xã/phường
    List<Hotel> findByWardId(Long wardId);

    // Tìm khách sạn theo quận/huyện thông qua quan hệ ward
    @Query("SELECT h FROM Hotel h WHERE h.ward.district.id = :districtId")
    List<Hotel> findByDistrictId(@Param("districtId") Long districtId);

    // Tìm khách sạn theo tỉnh/thành thông qua quan hệ ward → district → province
    @Query("SELECT h FROM Hotel h WHERE h.ward.district.province.id = :provinceId")
    List<Hotel> findByProvinceId(@Param("provinceId") Long provinceId);

    @Query("SELECT h FROM Hotel h JOIN FETCH h.ward w JOIN FETCH w.district d JOIN FETCH d.province p WHERE h.id = :hotelId")
    Hotel findHotelWithAddress(@Param("hotelId") Long hotelId);

}
