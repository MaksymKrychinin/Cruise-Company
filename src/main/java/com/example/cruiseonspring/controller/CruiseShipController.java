package com.example.cruiseonspring.controller;

import com.example.cruiseonspring.dto.cruiseship.CruiseShipDtoForUser;
import com.example.cruiseonspring.dto.cruiseship.CruiseShipDtoValid;
import com.example.cruiseonspring.entity.CruiseShip;
import com.example.cruiseonspring.service.CruiseShipService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cruiseShips")
@AllArgsConstructor
@CrossOrigin
public class CruiseShipController {
    private final CruiseShipService cruiseshipService;

    @GetMapping("")
    public List<CruiseShipDtoForUser> getAllCruiseShips() {
        return cruiseshipService.getAllCruiseShips();
    }

    @GetMapping("/{id}")
    public CruiseShipDtoForUser getDepartmentById(@PathVariable Integer id) {
        return cruiseshipService.getCruiseShipById(id);
    }

    @PostMapping("")
    public CruiseShip saveCruiseship(@Validated @RequestBody CruiseShipDtoValid cruiseShip) {
        return cruiseshipService.saveCruiseShip(cruiseShip);
    }

    @PutMapping("")
    public CruiseShip updateCruiseship(@Validated @RequestBody CruiseShipDtoValid cruiseShipDto) {
        return cruiseshipService.updateCruiseShip(cruiseShipDto);
    }

}
