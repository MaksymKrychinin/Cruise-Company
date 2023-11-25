package com.example.cruiseonspring.service;

import com.example.cruiseonspring.Utils.ValidationUtils;
import com.example.cruiseonspring.dto.CruiseShipDto;
import com.example.cruiseonspring.entity.CruiseShip;
import com.example.cruiseonspring.exception.NotFoundException;
import com.example.cruiseonspring.exception.ValidationException;
import com.example.cruiseonspring.mapper.CruiseShipMapper;
import com.example.cruiseonspring.repository.CruiseShipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class CruiseShipService {
    private final CruiseShipRepository cruiseshipRepository;
    private final ValidationUtils validationUtils;
    private final CruiseShipMapper cruiseShipMapper;
    private final JmsProducerService jmsProducerService;

    public Page<CruiseShip> getAllCruiseShips(Pageable pageable) {
        Page<CruiseShip> cruiseShipPage = cruiseshipRepository
                .findAllWhereOrderedSeatsLessThanCapacity(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));
        if (cruiseShipPage.isEmpty())
            throw new NotFoundException("List of Cruise ships not found");
        return cruiseShipPage;
    }


    public CruiseShip getCruiseShipById(Integer id) {
        return cruiseshipRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("CruiseShip " + id + "not found"));
    }


    public CruiseShip saveCruiseShip(CruiseShipDto cruiseShipDto) {
        validationUtils.validate(cruiseShipDto);
        LocalDate startDate = cruiseShipDto.getStartDate();
        LocalDate endDate = cruiseShipDto.getEndDate();
        if (startDate.isAfter(endDate)) {
            throw new ValidationException("Start date cannot be greater than end date");
        }
        CruiseShip cruiseShip = cruiseShipMapper.cruiseShipToDto(cruiseShipDto);
        CruiseShip savedCruiseShip = cruiseshipRepository.save(cruiseShip);
        jmsProducerService.sendToQueue(savedCruiseShip);
        return savedCruiseShip;
    }


    public void deleteCruiseShip(Integer id) {
        cruiseshipRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("CruiseShip " + id + "not found"));
        cruiseshipRepository.deleteById(id);
    }
}
