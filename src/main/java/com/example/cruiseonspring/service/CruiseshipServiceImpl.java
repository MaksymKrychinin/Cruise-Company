package com.example.cruiseonspring.service;

import com.example.cruiseonspring.dto.CruiseshipDto;
import com.example.cruiseonspring.exception.CruiseshipNotFoundException;
import com.example.cruiseonspring.mapper.CruiseshipMapper;
import com.example.cruiseonspring.repository.CruiseshipRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CruiseshipServiceImpl implements CruiseshipService {
    private final CruiseshipMapper cruiseshipMapper;
    private final CruiseshipRepository cruiseshipRepository;

    @Override
    public List<CruiseshipDto> getAllCruiseships() {
        return cruiseshipRepository
                .findAllWhereOrderedSeatsLessThanCapacity()
                .stream()
                .map(cruiseshipMapper::cruiseshipToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CruiseshipDto getCruiseshipById(Integer id) {
        return cruiseshipMapper.cruiseshipToDto(
                cruiseshipRepository
                        .findById(id)
                        .orElseThrow(()->
                                new CruiseshipNotFoundException("Cruiseship "+ id + "not found")));
    }

    @Override
    public CruiseshipDto saveCruiseship(CruiseshipDto cruiseshipDto) {
        return cruiseshipMapper.cruiseshipToDto(
                cruiseshipRepository.save(
                        cruiseshipMapper.dtoToCruiseship(cruiseshipDto)));
    }

    @Override
    public CruiseshipDto updateCruiseship(CruiseshipDto cruiseshipDto) {
        return cruiseshipMapper.cruiseshipToDto(
                cruiseshipRepository.save(
                        cruiseshipMapper.dtoToCruiseship(cruiseshipDto)));
    }

    @Override
    public void deleteCruiseship(Integer cruiseshipId) {
        cruiseshipRepository.deleteById(cruiseshipId);
    }
}
