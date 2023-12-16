package com.example.cruiseonspring;

import com.example.cruiseonspring.dto.UserOrderDto;
import com.example.cruiseonspring.entity.CruiseShip;
import com.example.cruiseonspring.entity.User;
import com.example.cruiseonspring.entity.UserOrder;
import com.example.cruiseonspring.exception.FailedToAccessException;
import com.example.cruiseonspring.exception.NotFoundException;
import com.example.cruiseonspring.mapper.UserOrderMapper;
import com.example.cruiseonspring.repository.CruiseShipRepository;
import com.example.cruiseonspring.repository.UserOrderRepository;
import com.example.cruiseonspring.repository.UserRepository;
import com.example.cruiseonspring.service.UserOrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@PropertySource("classpath:application-test.yml")
public class UserOrderServiceTest {

    @InjectMocks
    private UserOrderService userOrderService;

    @Mock
    private UserOrderRepository userOrderRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CruiseShipRepository cruiseShipRepository;

    @Mock
    private UserOrderMapper userOrderMapper;

    @Mock
    private User userDetails;
    @Mock
    private UserOrder userOrder;
    @Mock
    private CruiseShip cruiseShip;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        var userDetailsForWorkOrder = User.builder()
                .id(1)
                .email("user@mail.com")
                .password("password")
                .dateOfBirthday(LocalDate.now().minusYears(10))
                .gender("Male")
                .name("userName")
                .surname("userSurname")
                .phoneNumber("+38099999999").build();
        var cruiseShipForWorkOrder = CruiseShip.builder()
                .id(1)
                .capacity(10)
                .startDate(LocalDate.now().plusYears(10))
                .endDate(LocalDate.now().plusYears(30))
                .numberOfVisitedPorts(10).orderedSeats(0)
                .routeFrom("Ukr").routeTo("Eng").build();
        userOrder = UserOrder
                .builder()
                .user(userDetailsForWorkOrder)
                .cruiseShip(cruiseShipForWorkOrder)
                .backPassport("www.google.com")
                .frontPassport("www.google.com")
                .status("NotAproved")
                .build();
        userDetails = User.builder()
                .id(1)
                .email("user@mail.com")
                .password("password")
                .dateOfBirthday(LocalDate.now().minusYears(10))
                .gender("Male")
                .name("userName")
                .surname("userSurname")
                .phoneNumber("+38099999999").build();
        cruiseShip = CruiseShip.builder()
                .id(1)
                .capacity(10)
                .startDate(LocalDate.now().plusYears(10))
                .endDate(LocalDate.now().plusYears(30))
                .numberOfVisitedPorts(10).orderedSeats(0)
                .routeFrom("Ukr").routeTo("Eng").build();
    }

    @Test
    public void getUserOrderByIdReturnsCorrectOrder() {
        User mockUser = mock(User.class);
        when(mockUser.getEmail()).thenReturn(userDetails.getUsername());
        userOrder.setUser(mockUser);
        when(userOrderRepository.findById(anyInt())).thenReturn(Optional.of(userOrder));
        when(userOrderMapper.userOrderToDto(userOrder)).thenReturn(new UserOrderDto());

        UserOrderDto result = userOrderService.getUserOrderById(1, userDetails);

        assertNotNull(result);
    }

    @Test
    public void getUserOrderByIdThrowsNotFoundExceptionWhenOrderDoesNotExist() {
        when(userOrderRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> userOrderService.getUserOrderById(1, userDetails));
    }

    @Test
    public void getAllUserOrdersReturnsCorrectOrders() {
        List<UserOrder> userOrders = new ArrayList<>();
        userOrders.add(userOrder);
        when(userOrderRepository.findAllByUserEmail(anyString(), any(PageRequest.class))).thenReturn(userOrders);
        when(userOrderMapper.userOrderToDto(userOrder)).thenReturn(new UserOrderDto());

        Page<UserOrderDto> result = userOrderService.getAllUserOrders(userDetails, PageRequest.of(0, 10));

        assertEquals(1, result.getTotalElements());
    }

    @Test
    public void saveUserOrderReturnsSavedOrder() {
        when(cruiseShipRepository.findById(1)).thenReturn(Optional.of(cruiseShip));
        when(userRepository.findByEmail(userDetails.getEmail())).thenReturn(Optional.of(userDetails));
        when(userOrderRepository.save(userOrder)).thenReturn(userOrder);
        when(userOrderMapper.userOrderToDto(userOrder)).thenReturn(UserOrderDto.builder().id(1).user(userDetails)
                .cruiseShip(cruiseShip)
                .status("NotAproved")
                .build());
        UserOrderDto result = userOrderService.saveUserOrder(userOrder, userDetails);
        assertNotNull(result);
        assertNotNull(result.getUser());
        assertNotNull(result.getId());
        assertNotNull(result.getCruiseShip());
        assertNotNull(result.getStatus());
        assertEquals(result.getUser(), userDetails);
        assertEquals(result.getCruiseShip(), cruiseShip);
        assertEquals(result.getId(), 1);
    }

    @Test
    public void saveUserOrderThrowsFailedToAccessExceptionWhenNotEnoughSeats() {
        cruiseShip.setOrderedSeats(cruiseShip.getCapacity());
        when(cruiseShipRepository.findById(1)).thenReturn(Optional.of(cruiseShip));

        assertThrows(FailedToAccessException.class, () -> userOrderService.saveUserOrder(userOrder, userDetails));
    }


    @Test
    public void updateUserOrderThrowsFailedToAccessExceptionWhenUserCannotAccessOrder() {
        when(userOrderRepository.findById(userOrder.getId())).thenReturn(Optional.of(userOrder));
        userDetails.setEmail("not@email.com");
        assertThrows(FailedToAccessException.class, () -> userOrderService.updateUserOrder(userOrder, userDetails));
    }

    @Test
    public void deleteUserOrderDeletesOrder() {
        when(userOrderRepository.findById(anyInt())).thenReturn(Optional.of(userOrder));

        userOrderService.deleteUserOrder(1);

        verify(userOrderRepository, times(1)).deleteById(1);
    }

    @Test
    public void deleteUserOrderThrowsNotFoundExceptionWhenOrderDoesNotExist() {
        when(userOrderRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> userOrderService.deleteUserOrder(1));
    }
}