package com.example.cruiseonspring.controller;

import com.example.cruiseonspring.dto.CruiseShipDto;
import com.example.cruiseonspring.entity.CruiseShip;
import com.example.cruiseonspring.service.CruiseShipService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public ResponseEntity<CruiseShip> getDepartmentById(
            @PathVariable Integer id) {
        return ResponseEntity.ok().body(cruiseshipService.getCruiseShipById(id));
    }

    @PostMapping("")
    public ResponseEntity<CruiseShip> saveCruiseShip(
            @Validated @RequestBody CruiseShipDto cruiseShip) {
        return ResponseEntity.ok().body(cruiseshipService.saveCruiseShip(cruiseShip));
    }

    @PutMapping("")
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
