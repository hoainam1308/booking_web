package com.example.booking_web.repository;


import com.example.booking_web.entity.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, Long> {
    // Tìm tỉnh/thành phố theo tên
    List<Province> findByNameContaining(String name);
}
