package com.example.booking_web.controller;

import com.example.booking_web.entity.District;
import com.example.booking_web.service.IDistrictService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/district")
public class DistrictController {
    private final IDistrictService districtService;
    public DistrictController(IDistrictService districtService){
         this.districtService = districtService;
    }
    @GetMapping("/by-province/{provinceId}")
    public List<District> getDistrictByProvince(@PathVariable Long provinceId) {
        return districtService.findDistrictsByProvince(provinceId);
    }

}
