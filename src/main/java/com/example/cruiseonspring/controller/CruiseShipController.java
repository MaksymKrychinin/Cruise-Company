package com.example.cruiseonspring.controller;

import com.example.cruiseonspring.dto.CruiseShipDto;
import com.example.cruiseonspring.entity.CruiseShip;
import com.example.cruiseonspring.service.CruiseShipService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cruiseShips")
@RequiredArgsConstructor
@CrossOrigin
public class CruiseShipController {
    private final CruiseShipService cruiseshipService;

    @GetMapping("")
    public List<CruiseShipDto> getAllCruiseShips() {
        return cruiseshipService.getAllCruiseShips();
    }

    @GetMapping("/{id}")
    public CruiseShipDto getDepartmentById(
            @PathVariable Integer id
    ) {
        return cruiseshipService.getCruiseShipById(id);
    }

    @PostMapping("")
    public CruiseShip saveCruiseship(
            @Validated @RequestBody CruiseShipDto cruiseShip
    ) {
        return cruiseshipService.saveCruiseShip(cruiseShip);
    }

    @PutMapping("")
    public CruiseShip updateCruiseship(
            @Validated @RequestBody CruiseShipDto cruiseShipDto
    ) {
        return cruiseshipService.updateCruiseShip(cruiseShipDto);
    }

}
