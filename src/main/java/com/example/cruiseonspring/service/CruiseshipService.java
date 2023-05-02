package com.example.cruiseonspring.service;

import com.example.cruiseonspring.dto.CruiseshipDto;

import java.util.List;

public interface CruiseshipService {
    List<CruiseshipDto> getAllCruiseships();

    CruiseshipDto getCruiseshipById(Integer id);

    CruiseshipDto saveCruiseship(CruiseshipDto cruiseshipDto);

    CruiseshipDto updateCruiseship(CruiseshipDto cruiseshipDto);

    void deleteCruiseship(Integer integer);
}
