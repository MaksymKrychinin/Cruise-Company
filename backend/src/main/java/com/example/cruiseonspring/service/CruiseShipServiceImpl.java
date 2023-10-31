package com.example.cruiseonspring.service;

import com.example.cruiseonspring.Utils.ValidationUtils;
import com.example.cruiseonspring.dto.CruiseShipDto;
import com.example.cruiseonspring.entity.CruiseShip;
import com.example.cruiseonspring.exception.NotFoundException;
import com.example.cruiseonspring.mapper.CruiseShipMapper;
import com.example.cruiseonspring.repository.CruiseShipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CruiseShipServiceImpl implements CruiseShipService {
    private final CruiseShipRepository cruiseshipRepository;
    private final ValidationUtils validationUtils;
    private final CruiseShipMapper cruiseShipMapper;

    @Override
    public List<CruiseShip> getAllCruiseShips() {
        List<CruiseShip> cruiseShipList = cruiseshipRepository
                .findAllWhereOrderedSeatsLessThanCapacity();
        if (cruiseShipList.size() == 0)
            throw new NotFoundException("List of Cruise ships not found");
        return cruiseShipList;
    }

    @Override
    public CruiseShip getCruiseShipById(Integer id) {
        return cruiseshipRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("CruiseShip " + id + "not found"));
    }

    @Override
    public CruiseShip saveCruiseShip(CruiseShipDto cruiseShipDto) {
        validationUtils.validate(cruiseShipDto);
        Date startDate = cruiseShipDto.getStartDate();
        Date endDate = cruiseShipDto.getEndDate();
        if (startDate.compareTo(endDate) > 0) {
            throw new NotFoundException("Start date cannot be greater than end date");
        }
        CruiseShip cruiseShip = cruiseShipMapper.cruiseShipToDto(cruiseShipDto);
        return cruiseshipRepository
                .save(cruiseShip);
    }

    public CruiseShip updateCruiseShipOrderedSeatsPlusOne(Integer id) {
        CruiseShip cruiseShip = cruiseshipRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("CruiseShip " + id + "not found"));
        return cruiseshipRepository.updateCruiseShipOrderedSeatsPlusOne(id);
    }

    @Override
    public void deleteCruiseShip(Integer id) {
        cruiseshipRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("CruiseShip " + id + "not found"));
        cruiseshipRepository.deleteById(id);
    }
}
