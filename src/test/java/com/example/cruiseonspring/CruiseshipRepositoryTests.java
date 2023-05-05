package com.example.cruiseonspring;

import com.example.cruiseonspring.entity.Cruiseship;
import com.example.cruiseonspring.mapper.CruiseshipMapper;
import com.example.cruiseonspring.repository.CruiseshipRepository;
import com.example.cruiseonspring.service.CruiseshipService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
class CruiseRepositoryTests {
    @Autowired
    CruiseshipRepository cruiseshipRepository;
    @Autowired
    CruiseshipMapper cruiseshipMapper;
    @Autowired
    CruiseshipService cruiseshipService;

    @Test
    void contextLoads() {
        //todo CLEAR DB If exist
        //Todo Create data in db
        System.out.println(cruiseshipRepository.findAll().size());
        cruiseshipRepository.findAll().forEach(System.out::println);
    }

    @Test
    void testRepositorySaveCruiseShipNotNullIdAndAddedOneResult() {
        Cruiseship cruiseShipFullValues = new Cruiseship(
                1,
                10,
                "Ukraine",
                "Europe",
                5,
                new Date(2015, 6, 12),
                new Date(2015, 6, 17),
                0
        );
        Cruiseship savedCruiseShip = cruiseshipRepository.save(cruiseShipFullValues);
        assertEquals(cruiseShipFullValues.toString(), savedCruiseShip.toString());

        Integer id = cruiseShipFullValues.getId();
        System.out.println("id = " + id);
        Integer id1 = savedCruiseShip.getId();
        System.out.println("id1 = " + id1);

        int size = cruiseshipRepository.findAll().size();
        assertEquals(1, size);

        System.out.println("size = " + size);
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
        Integer idCruiseShipWithNull = cruiseShipWithNullId.getId();
        Integer idSavedCruiseShip = savedCruiseShip.getId();
        assertEquals(idCruiseShipWithNull, idSavedCruiseShip);
        System.out.println("idSavedCruiseShip = " + idSavedCruiseShip);
        System.out.println("idCruiseShipWithNull = " + idCruiseShipWithNull);
        int size = cruiseshipRepository.findAll().size();
        System.out.println("size = " + size);
        assertTrue(size > 1);

        Cruiseship cruiseShipWithNullIdAnother = new Cruiseship(
                null,
                10,
                "Ukraine",
                "Europe",
                5,
                new Date(2015, 6, 12),
                new Date(2015, 6, 17),
                0
        );
        Cruiseship savedCruiseShipAnother =
                cruiseshipRepository.save(cruiseShipWithNullIdAnother);

        assertEquals(cruiseShipWithNullIdAnother.toString(), savedCruiseShipAnother.toString());

        int sizeAnother = cruiseshipRepository
                .findAll()
                .size();

        assertEquals(sizeAnother, size + 1);
    }

    @Test
    void testRepositorySaveEntityWithIdOneEqualsAddedEntity() {
        Cruiseship cruiseShipFullValues = new Cruiseship(
                1,
                10,
                "Ukraine",
                "Europe",
                5,
                new Date(2015, 6, 12),
                new Date(2015, 6, 17),
                0
        );
        cruiseshipRepository.save(cruiseShipFullValues);
        Optional<Cruiseship> entityById = cruiseshipRepository.findById(1);
        assertEquals(entityById.get().toString(), cruiseShipFullValues.toString());
    }

    @Test
    void testRepositoryFindAllByCapacityGreaterThanEqual(){
        List<Cruiseship> allByOrderedSeatsLessThanCapacity = cruiseshipRepository.findAllWhereOrderedSeatsLessThanCapacity();

        assertEquals(1, allByOrderedSeatsLessThanCapacity.size());
        assertEquals(1, cruiseshipRepository.count());

        Cruiseship cruiseShipFullValues = new Cruiseship(
                null,
                10,
                "Ukraine",
                "Europe",
                5,
                new Date(2015, 6, 12),
                new Date(2015, 6, 17),
                10
        );
        cruiseshipRepository.save(cruiseShipFullValues);

        assertEquals(2, cruiseshipRepository.count());

        allByOrderedSeatsLessThanCapacity = cruiseshipRepository.findAllWhereOrderedSeatsLessThanCapacity();

        System.out.println(allByOrderedSeatsLessThanCapacity.size());
        assertEquals(1, allByOrderedSeatsLessThanCapacity.size());
        assertEquals(2, cruiseshipRepository.count());
    }

    @Test
    void testRepositoryDelete(){
        assertEquals(1, cruiseshipRepository.count());
        cruiseshipRepository.deleteById(1);
        assertEquals(0, cruiseshipRepository.count());
    }
    @Test
    void testRepositoryUpdate(){
        Cruiseship cruiseShipFullValues = new Cruiseship(
                1,
                10,
                "Ukraine",
                "Europe",
                5,
                new Date(2015, 6, 12),
                new Date(2015, 6, 17),
                10
        );
        Cruiseship cruiseship = cruiseshipRepository.save(cruiseShipFullValues);
        cruiseship.setCapacity(100);
        cruiseshipRepository.save(cruiseship);
        Optional<Cruiseship> cruiseshipById = cruiseshipRepository.findById(1);

        assertEquals(100, cruiseshipById.get().getCapacity());
    }
}
