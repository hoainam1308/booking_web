package com.example.booking_web.repository;

import com.example.booking_web.entity.HotelFacility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelFacilityRepository extends JpaRepository<HotelFacility, Long> {
}
