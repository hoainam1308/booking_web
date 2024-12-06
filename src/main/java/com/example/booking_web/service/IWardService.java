package com.example.booking_web.service;

import com.example.booking_web.entity.Ward;

import java.util.List;

public interface IWardService {
    public List<Ward> findAllWards();

    public List<Ward> findWardsByDistrict(Long districtId);

    public List<Ward> searchWardsByName(String name);

    public Ward findByCode(int code);
}
