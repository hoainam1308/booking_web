package com.example.booking_web.service;

import com.example.booking_web.entity.District;

import java.util.List;

public interface IDistrictService {

    public List<District> findAllDistricts();

    public List<District> findDistrictsByProvince(Long provinceId);

    public List<District> searchDistrictsByName(String name);
}
