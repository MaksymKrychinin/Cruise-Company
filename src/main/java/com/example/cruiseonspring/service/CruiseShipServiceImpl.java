package com.example.cruiseonspring.service;

import com.example.cruiseonspring.dto.CruiseShipDto;
import com.example.cruiseonspring.entity.CruiseShip;
import com.example.cruiseonspring.exception.CruiseshipNotFoundException;
import com.example.cruiseonspring.mapper.CruiseShipDtoToEntityMapper;
import com.example.cruiseonspring.mapper.CruiseShipToDtoMapper;
import com.example.cruiseonspring.mapper.MapperUser;
import com.example.cruiseonspring.repository.CruiseShipRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public List<CruiseShipDto> getAllCruiseShips() {
        List<CruiseShip> cruiseShipList = cruiseshipRepository
                .findAllWhereOrderedSeatsLessThanCapacity();
        if (cruiseShipList.size() == 0)
            throw new CruiseshipNotFoundException("List of Cruise ships not found");
        return cruiseShipList
                .stream()
                .map(cruiseShipToDtoMapper)
                .collect(Collectors.toList());
    }

    @Override
    public CruiseShipDto getCruiseShipById(Integer id) {
        return cruiseshipRepository
                .findById(id)
                .map(cruiseShipToDtoMapper)
                .orElseThrow(() ->
                        new CruiseshipNotFoundException("CruiseShip " + id + "not found"));
    }

    @Override
    public CruiseShip saveCruiseShip(CruiseShipDto cruiseShip) {
        return cruiseshipRepository
                .save(cruiseShipDtoToEntityMapper.apply(cruiseShip));
    }

    @Override
    public CruiseShip updateCruiseShip(CruiseShipDto cruiseShip) {
        return cruiseshipRepository.save(cruiseShipDtoToEntityMapper.apply(cruiseShip));
    }

    @Override
    public void deleteCruiseShip(Integer id) {
        cruiseshipRepository.deleteById(id);
    }
}
