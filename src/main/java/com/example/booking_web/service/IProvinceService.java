package com.example.booking_web.service;

import com.example.booking_web.entity.Province;

import java.util.List;

public interface IProvinceService {
    public List<Province> findAllProvinces();

    public List<Province> searchProvincesByName(String name);
}
