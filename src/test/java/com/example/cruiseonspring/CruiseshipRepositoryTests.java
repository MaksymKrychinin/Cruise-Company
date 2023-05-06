package com.example.cruiseonspring;

import com.example.cruiseonspring.entity.Cruiseship;
import com.example.cruiseonspring.mapper.CruiseshipMapper;
import com.example.cruiseonspring.repository.CruiseshipRepository;
import com.example.cruiseonspring.service.CruiseshipService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CruiseRepositoryTests {

    @Autowired
    CruiseshipRepository cruiseshipRepository;
    @Autowired
    CruiseshipMapper cruiseshipMapper;
    @Autowired
    CruiseshipService cruiseshipService;

    @BeforeEach
    void deleteContext() {
        cruiseshipRepository.deleteAll();
        cruiseshipRepository.findAll().forEach(System.out::println);
    }

    @Test
    void testRepositorySaveCruiseShipNotNullIdAndAddedOneResult() {
        Cruiseship cruiseShipFullValues = new Cruiseship(
                1,
                10,
                "Notherland",
                "Europe",
                5,
                new Date(2015, 6, 12),
                new Date(2015, 6, 17),
                0
        );
        Cruiseship savedCruiseShip = cruiseshipRepository.save(cruiseShipFullValues);
        assertEquals(cruiseShipFullValues.toString(), savedCruiseShip.toString());

        int countOfAddedValues = (int) cruiseshipRepository.count();
        assertEquals(1, countOfAddedValues);

        cruiseShipFullValues = new Cruiseship(
                1,
                10,
                "Europe",
                "Northerland",
                5,
                new Date(12, 6, 12),
                new Date(19, 6, 17),
                0
        );

        savedCruiseShip = cruiseshipRepository.save(cruiseShipFullValues);
        assertEquals(1, savedCruiseShip.getId());

        countOfAddedValues = (int) cruiseshipRepository.count();
        assertEquals(1, countOfAddedValues);
    }

    @Test
    void testRepositorySaveCruiseShipNullId() {
        Cruiseship cruiseShipWithNullId = new Cruiseship(
                null,
                10,
                "Ukraine",
                "Europe",
                5,
                new Date(2015, 6, 12),
                new Date(2015, 6, 17),
                0
        );

        Cruiseship savedCruiseShip = cruiseshipRepository.save(cruiseShipWithNullId);
        assertEquals(1, savedCruiseShip.getId());

        int countOfAddedValues = (int) cruiseshipRepository.count();
        assertEquals(1, countOfAddedValues);

        Cruiseship cruiseShipWithNullIdAnother = new Cruiseship(
                null,
                10,
                "Europe",
                "Ukraine",
                5,
                new Date(2015, 6, 12),
                new Date(2015, 6, 17),
                0
        );
        Cruiseship savedCruiseShipAnother =
                cruiseshipRepository.save(cruiseShipWithNullIdAnother);

        assertEquals(cruiseShipWithNullIdAnother.toString(), savedCruiseShipAnother.toString());

        int sizeAnother = (int) cruiseshipRepository.count();

        assertEquals(3, sizeAnother);
    }


    @Test
    void testRepositoryFindAllByCapacityGreaterThanEqual() {
        Cruiseship cruiseShipFullValues = new Cruiseship(
                null,
                10,
                "America",
                "Canada",
                5,
                new Date(2015, 6, 12),
                new Date(2015, 6, 17),
                10
        );

        cruiseshipRepository.save(cruiseShipFullValues);
        assertEquals(1, cruiseshipRepository.count());

        Cruiseship cruiseShipFullValuesNext = new Cruiseship(
                null,
                10,
                "Canada",
                "America",
                5,
                new Date(2015, 6, 12),
                new Date(2015, 6, 17),
                0
        );
        cruiseshipRepository.save(cruiseShipFullValuesNext);
        assertEquals(2, cruiseshipRepository.count());

        List<Cruiseship> allWhereOrderedSeatsLessThanCapacity = cruiseshipRepository.findAllWhereOrderedSeatsLessThanCapacity();

        assertEquals(1, allWhereOrderedSeatsLessThanCapacity.size());
        assertEquals(2, cruiseshipRepository.count());
    }

    @Test
    void testRepositoryDelete() {
        Cruiseship cruiseShipFullValues = new Cruiseship(
                null,
                10,
                "Sweden",
                "Europe",
                5,
                new Date(2015, 6, 12),
                new Date(2015, 6, 17),
                10
        );
        cruiseshipRepository.save(cruiseShipFullValues);
        assertEquals(1, cruiseshipRepository.count());

        cruiseshipRepository.deleteById(1);
        assertEquals(0, cruiseshipRepository.count());
    }

    @Test
    void testRepositoryUpdate() {
        Cruiseship cruiseShipFullValues = new Cruiseship(
                1,
                10,
                "Europe",
                "Sweden",
                5,
                new Date(2015, 6, 12),
                new Date(2015, 6, 17),
                10
        );
        Cruiseship cruiseship = cruiseshipRepository.save(cruiseShipFullValues);
        assertEquals(1, cruiseshipRepository.count());
        cruiseship.setCapacity(100);
        cruiseshipRepository.save(cruiseship);
        assertEquals(1, cruiseshipRepository.count());

        Optional<Cruiseship> cruiseshipById = cruiseshipRepository.findById(1);
        assertEquals(100, cruiseshipById.get().getCapacity());
    }
}
