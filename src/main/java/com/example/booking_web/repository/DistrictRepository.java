package com.example.booking_web.repository;

import com.example.booking_web.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {
    // Tìm quận/huyện theo tên
    List<District> findByNameContaining(String name);

    // Tìm tất cả quận/huyện thuộc một tỉnh/thành
    List<District> findByProvinceId(Long provinceId);

}