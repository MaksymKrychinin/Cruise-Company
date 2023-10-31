package com.example.cruiseonspring.controller;

import com.example.cruiseonspring.dto.CruiseShipDto;
import com.example.cruiseonspring.entity.CruiseShip;
import com.example.cruiseonspring.service.CruiseShipService;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<List<CruiseShip>> getAllCruiseShips() {
        return ResponseEntity.ok().body(cruiseshipService.getAllCruiseShips());
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

/*    @PutMapping("")
    public ResponseEntity<CruiseShip> updateCruiseShip(
            @Validated @RequestBody CruiseShipDto cruiseShipDto) {
        return ResponseEntity.ok().body(cruiseshipService.update(cruiseShipDto));
    }*/

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCruiseShip(
            @PathVariable Integer id) {
        cruiseshipService.deleteCruiseShip(id);
        return ResponseEntity.ok().body("Deleted");
    }

}
