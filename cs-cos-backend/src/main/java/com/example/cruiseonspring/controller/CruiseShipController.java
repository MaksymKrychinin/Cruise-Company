package com.example.cruiseonspring.controller;

import com.example.cruiseonspring.dto.CruiseShipDto;
import com.example.cruiseonspring.dto.SpecificationTransferDto;
import com.example.cruiseonspring.entity.CruiseShip;
import com.example.cruiseonspring.service.CruiseShipService;
import dtos.FilterFieldsDto;
import functions.FilterFieldCheck;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cruise-ships")
@RequiredArgsConstructor
@CrossOrigin
public class CruiseShipController {
    private final CruiseShipService cruiseshipService;

    @GetMapping("")
    public ResponseEntity<Page<CruiseShip>> getAllCruiseShips(
            @PageableDefault Pageable pageable) {
        return ResponseEntity.ok().body(cruiseshipService.getAllCruiseShips(pageable));
    }

    @GetMapping("/filters")
    public List<FilterFieldsDto> objectFiltersCruiseShip() {
        return FilterFieldCheck.listOfObjectFilters(CruiseShip.class);
    }

    @GetMapping("/filtered")
    ResponseEntity<Page<CruiseShipDto>> getAllCruiseShipsFiltered(
            @PageableDefault Pageable pageable,
            SpecificationTransferDto[] specificationTransferDto) {
        Page<CruiseShipDto> allCruiseShips = cruiseshipService.getAllCruiseShipsFiltered(pageable, specificationTransferDto);
        return ResponseEntity.ok().body(allCruiseShips);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CruiseShip> getDepartmentById(
            @PathVariable Integer id) {
        return ResponseEntity.ok().body(cruiseshipService.getCruiseShipById(id));
    }

    @PostMapping("/")
    public ResponseEntity<CruiseShip> saveCruiseShip(
            @Validated @RequestBody CruiseShipDto cruiseShip) {
        return ResponseEntity.ok().body(cruiseshipService.saveCruiseShip(cruiseShip));
    }

    @PutMapping("/")
    public ResponseEntity<CruiseShip> updateCruiseShip(
            @Validated @RequestBody CruiseShipDto cruiseShipDto) {
        return ResponseEntity.ok().body(cruiseshipService.saveCruiseShip(cruiseShipDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCruiseShip(
            @PathVariable Integer id) {
        cruiseshipService.deleteCruiseShip(id);
        return ResponseEntity.ok().body("Deleted");
    }

}
