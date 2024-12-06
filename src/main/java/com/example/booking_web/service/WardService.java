package com.example.booking_web.service;

import com.example.booking_web.entity.Ward;
import com.example.booking_web.repository.WardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WardService implements IWardService {
    private final WardRepository wardRepository;

    @Autowired
    public WardService(WardRepository wardRepository) {
        this.wardRepository = wardRepository;
    }

    public List<Ward> findAllWards() {
        return wardRepository.findAll();
    }

    public List<Ward> findWardsByDistrict(Long districtId) {
        return wardRepository.findByDistrictId(districtId);
    }

    public List<Ward> searchWardsByName(String name) {
        return wardRepository.findByNameContaining(name);
    }

    public Ward findByCode(int code) {
        return wardRepository.findByCode(code);
    }
}
