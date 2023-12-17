package com.example.cruiseonspring;

import com.example.cruiseonspring.Utils.ValidationUtils;
import com.example.cruiseonspring.dto.CruiseShipDto;
import com.example.cruiseonspring.entity.CruiseShip;
import com.example.cruiseonspring.exception.NotFoundException;
import com.example.cruiseonspring.exception.ValidationException;
import com.example.cruiseonspring.mapper.CruiseShipMapper;
import com.example.cruiseonspring.repository.CruiseShipRepository;
import com.example.cruiseonspring.service.CruiseShipService;
import com.example.cruiseonspring.service.JmsProducerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@PropertySource("classpath:application-test.yml")
public class CruiseShipServiceTest {

    @InjectMocks
    private CruiseShipService cruiseShipService;

    @Mock
    private CruiseShipRepository cruiseShipRepository;

    @Mock
    private ValidationUtils validationUtils;

    @Mock
    private CruiseShipMapper cruiseShipMapper;

    @Mock
    private JmsProducerService jmsProducerService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllCruiseShipsReturnsPageWhenCruiseShipsExist() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<CruiseShip> cruiseShipPage = mock(Page.class);
        when(cruiseShipRepository.findAllWhereOrderedSeatsLessThanCapacity(pageable)).thenReturn(cruiseShipPage);
        when(cruiseShipPage.isEmpty()).thenReturn(false);

        Page<CruiseShip> result = cruiseShipService.getAllCruiseShips(pageable);

        assertEquals(cruiseShipPage, result);
    }

    @Test
    public void getAllCruiseShipsThrowsNotFoundExceptionWhenNoCruiseShipsExist() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<CruiseShip> cruiseShipPage = mock(Page.class);
        when(cruiseShipRepository.findAllWhereOrderedSeatsLessThanCapacity(pageable)).thenReturn(cruiseShipPage);
        when(cruiseShipPage.isEmpty()).thenReturn(true);

        assertThrows(NotFoundException.class, () -> cruiseShipService.getAllCruiseShips(pageable));
    }

    @Test
    public void getCruiseShipByIdReturnsCruiseShipWhenExists() {
        Integer id = 1;
        CruiseShip cruiseShip = new CruiseShip();
        when(cruiseShipRepository.findById(id)).thenReturn(Optional.of(cruiseShip));

        CruiseShip result = cruiseShipService.getCruiseShipById(id);

        assertEquals(cruiseShip, result);
    }

    @Test
    public void getCruiseShipByIdThrowsNotFoundExceptionWhenDoesNotExist() {
        Integer id = 1;
        when(cruiseShipRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> cruiseShipService.getCruiseShipById(id));
    }

    @Test
    public void saveCruiseShipReturnsSavedCruiseShip() {
        CruiseShipDto cruiseShipDto = CruiseShipDto.builder()
                .capacity(10)
                .startDate(LocalDate.now().plusYears(10))
                .endDate(LocalDate.now().plusYears(30))
                .numberOfVisitedPorts(10).orderedSeats(0)
                .routeFrom("Ukr").routeTo("Eng").build();

        CruiseShip cruiseShip = CruiseShip.builder()
                .capacity(10)
                .startDate(LocalDate.now().plusYears(10))
                .endDate(LocalDate.now().plusYears(30))
                .numberOfVisitedPorts(10).orderedSeats(0)
                .routeFrom("Ukr").routeTo("Eng").build();
        when(cruiseShipMapper.dtoToCruiseShip(cruiseShipDto)).thenReturn(cruiseShip);
        when(cruiseShipRepository.save(cruiseShip)).thenReturn(cruiseShip);

        CruiseShip result = cruiseShipService.saveCruiseShip(cruiseShipDto);

        assertEquals(cruiseShip, result);
    }

    @Test
    public void saveCruiseShipThrowsNotFoundExceptionWhenStartDateGreaterThanEndDate() {
        CruiseShipDto cruiseShipDto = new CruiseShipDto();
        cruiseShipDto.setStartDate(LocalDate.now().plusYears(10));
        cruiseShipDto.setEndDate(LocalDate.now().plusYears(1));

        assertThrows(ValidationException.class, () -> cruiseShipService.saveCruiseShip(cruiseShipDto));
    }

    @Test
    public void deleteCruiseShipDeletesWhenExists() {
        Integer id = 1;
        CruiseShip cruiseShip = new CruiseShip();
        when(cruiseShipRepository.findById(id)).thenReturn(Optional.of(cruiseShip));

        cruiseShipService.deleteCruiseShip(id);

        verify(cruiseShipRepository, times(1)).deleteById(id);
    }

    @Test
    public void deleteCruiseShipThrowsNotFoundExceptionWhenDoesNotExist() {
        Integer id = 1;
        when(cruiseShipRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> cruiseShipService.deleteCruiseShip(id));
    }
}