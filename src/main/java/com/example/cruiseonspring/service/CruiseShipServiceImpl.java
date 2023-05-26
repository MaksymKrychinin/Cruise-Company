package com.example.cruiseonspring.service;

import com.example.cruiseonspring.dto.cruiseship.CruiseShipDtoForUser;
import com.example.cruiseonspring.dto.cruiseship.CruiseShipDtoValid;
import com.example.cruiseonspring.entity.CruiseShip;
import com.example.cruiseonspring.exception.CruiseshipNotFoundException;
import com.example.cruiseonspring.mapper.cruiseship.CruiseShipDtoToEntityMapper;
import com.example.cruiseonspring.mapper.cruiseship.CruiseShipToDtoMapper;
import com.example.cruiseonspring.repository.CruiseShipRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CruiseShipServiceImpl implements CruiseShipService {
    private final CruiseShipRepository cruiseshipRepository;
    private final CruiseShipToDtoMapper cruiseShipToDtoMapper;
    private final CruiseShipDtoToEntityMapper cruiseShipDtoToEntityMapper;

    @Override
    public List<CruiseShipDtoForUser> getAllCruiseShips() {
        return cruiseshipRepository
                .findAllWhereOrderedSeatsLessThanCapacity()
                .stream()
                .map(cruiseShipToDtoMapper)
                .collect(Collectors.toList());
    }

    @Override
    public CruiseShipDtoForUser getCruiseShipById(Integer id) {
        return cruiseshipRepository
                .findById(id)
                .map(cruiseShipToDtoMapper)
                .orElseThrow(() ->
                        new CruiseshipNotFoundException("CruiseShip " + id + "not found"));
    }

    @Override
    public CruiseShip saveCruiseShip(CruiseShipDtoValid cruiseShip) {
        return cruiseshipRepository.save(cruiseShipDtoToEntityMapper.apply(cruiseShip));
    }

    @Override
    public CruiseShip updateCruiseShip(CruiseShipDtoValid cruiseShip) {
        return cruiseshipRepository.save(cruiseShipDtoToEntityMapper.apply(cruiseShip));
    }

    @Override
    public void deleteCruiseShip(Integer id) {
        cruiseshipRepository.deleteById(id);
    }
}
