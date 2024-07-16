package com.tccompany.tcuombackend.service;

import com.tccompany.tcuombackend.dto.SpecificationTransferDto;
import com.tccompany.tcuombackend.dto.UserOrderDto;
import com.tccompany.tcuombackend.entity.UserOrder;
import com.tccompany.tcuombackend.mapper.UserOrderMapper;
import com.tccompany.tcuombackend.repository.*;
import com.tccompany.tcuombackend.repository.specification.BaseFilterSpecification;
import com.tccompany.tcuombackend.repository.specification.BaseSpecificationFactory;
import com.tccompany.tcvalidation.exception.FailedToAccessException;
import com.tccompany.tcvalidation.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserOrderService {
    private final UserOrderRepository userorderRepository;
    private final UserOrderMapper userorderMapper;
    private final CruiseShipRepository cruiseShipRepository;
    private final UserOrderMapper userOrderMapper;
    private final BaseSpecificationFactory<UserOrder> userOrderBaseSpecificationFactory;


    public UserOrderDto getUserOrderById(Integer orderId, UserDetails userDetails) {
        UserOrder userOrder = userorderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("User order" + orderId + "not found"));
        if (!userOrder.getUser().getEmail().equals(userDetails.getUsername())) {
            throw new NotFoundException("User can't access another peoples orders");
        }
        return userorderMapper.userOrderToDto(userOrder);
    }


    public Page<UserOrderDto> getAllUserOrders(UserDetails user, Pageable pageable) {
        Page<UserOrder> allByUserEmail = userorderRepository
                .findAllByUserEmail(user.getUsername(), PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));
        Page<UserOrderDto> mappedToDtoPage = allByUserEmail.map(userOrderMapper::userOrderToDto);
        return mappedToDtoPage;
    }


    @Transactional(isolation = Isolation.SERIALIZABLE)
    public UserOrderDto saveUserOrder(UserOrder userOrder, UserDetails userDetails) {
        int id = userOrder.getCruiseShip().getId();
        CruiseShip cruiseShip = cruiseShipRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("CruiseShip " + id + "not found"));
        if (cruiseShip.getCapacity() <= cruiseShip.getOrderedSeats()) {
            throw new FailedToAccessException("Not enough seats");
        }

        userOrder.setUser(userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new NotFoundException("User not found")));
        userOrder.setCruiseShip(cruiseShip);
        userOrder.setStatus("Not approved");
        UserOrder save = userorderRepository.save(userOrder);
        this.updateCruiseShipOrderedSeatsPlusOne(id);
        return userorderMapper.userOrderToDto(save);
    }

    public void updateCruiseShipOrderedSeatsPlusOne(Integer id) {
        cruiseShipRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("CruiseShip " + id + "not found"));
        cruiseShipRepository.updateCruiseShipOrderedSeatsPlusOne(id);
    }


    public UserOrderDto updateUserOrder(UserOrder userOrder, UserDetails userDetails) {
        UserOrder userOrderToUpdate = userorderRepository.findById(
                        userOrder.getId())
                .orElseThrow(() -> new NotFoundException("User order not found"));
        if (!userOrderToUpdate.getUser().getEmail().equals(userDetails.getUsername())) {
            throw new FailedToAccessException("User can't access this order");
        }
        return userorderMapper.userOrderToDto(userorderRepository.save(userOrder));
    }


    public void deleteUserOrder(Integer id) {
        userorderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User order not found"));
        userorderRepository.deleteById(id);
    }

    public Page<UserOrderDto> getAllUserOrdersFiltered(UserDetails userDetails, Pageable pageable, SpecificationTransferDto[] specificationTransferDto) {
        Specification<UserOrder> userOrderBaseFilterSpecification =
                userOrderBaseSpecificationFactory.specificationColumnFilter(specificationTransferDto);
        BaseFilterSpecification<UserOrder> userDetailsBaseSpecification =
                userOrderBaseSpecificationFactory.specificationUserDetailsColumnFilter(userDetails.getUsername());
        return userorderRepository
                .findAll(userOrderBaseFilterSpecification.and(userDetailsBaseSpecification),
                        PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()))
                .map(userOrderMapper::userOrderToDto);
    }
}
