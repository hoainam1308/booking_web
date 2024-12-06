package com.example.booking_web.service;

import com.example.booking_web.entity.District;
import com.example.booking_web.repository.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictService implements IDistrictService{
    private final DistrictRepository districtRepository;

    @Autowired
    public DistrictService(DistrictRepository districtRepository) {
        this.districtRepository = districtRepository;
    }

    public List<District> findAllDistricts() {
        return districtRepository.findAll();
    }

    public List<District> findDistrictsByProvince(Long provinceId) {
        return districtRepository.findByProvinceId(provinceId);
    }

    public List<District> searchDistrictsByName(String name) {
        return districtRepository.findByNameContaining(name);
    }
}
