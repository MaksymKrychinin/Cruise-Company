package com.example.cruiseonspring.controller;

import com.example.cruiseonspring.dto.CruiseshipDto;
import com.example.cruiseonspring.service.CruiseshipService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cruiseship")
@AllArgsConstructor
@CrossOrigin
public class CruiseshipController {
    private final CruiseshipService cruiseshipService;

    @GetMapping("/getAll")
    public List<CruiseshipDto> getAllCruiseships() {
        return cruiseshipService.getAllCruiseships();
    }

    @PostMapping("/getById/{id}")
    public CruiseshipDto getDepartmentById(@PathVariable Integer id) {
        return cruiseshipService.getCruiseshipById(id);
    }

    @PostMapping("/save")
    public CruiseshipDto saveCruiseship(@Validated @RequestBody CruiseshipDto cruiseshipDto){
        return cruiseshipService.saveCruiseship(cruiseshipDto);
    }
    @PostMapping("/update")
    public CruiseshipDto updateCruiseship(@Validated @RequestBody CruiseshipDto cruiseshipDto){
        return cruiseshipService.updateCruiseship(cruiseshipDto);
    }
}
