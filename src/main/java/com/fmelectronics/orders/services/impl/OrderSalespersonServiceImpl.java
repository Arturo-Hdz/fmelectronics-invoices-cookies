package com.fmelectronics.orders.services.impl;

import com.fmelectronics.orders.DTOs.OrderDTOSalesperson;
import com.fmelectronics.orders.DTOs.UpdateStatusOrders;
import com.fmelectronics.orders.models.Order;
import com.fmelectronics.orders.models.enums.Statusorders;
import com.fmelectronics.orders.exceptions.NotFoundException;
import com.fmelectronics.orders.repositories.OrderSalespersonRepository;
import com.fmelectronics.orders.services.OrderSalespersonService;
import com.fmelectronics.orders.util.StandardResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author Arturo Hdez on 3/26/24;
 * @project back-orders
 */

@Service
@Slf4j
public class OrderSalespersonServiceImpl implements OrderSalespersonService {

    @Autowired
    OrderSalespersonRepository orderSalespersonRepository;

    @Override
    public ResponseEntity<?> update(OrderDTOSalesperson orders){
        Order varOrder = orderSalespersonRepository.findById(orders.getId())
                .orElseThrow(() -> new NotFoundException("Order Not found with id = " + orders.getId()));

        log.info("Order Seller Updated");
//        varOrder.setNo_order(orders.getNo_order());
//        varOrder.setDate(orders.getDate());
//        varOrder.setCustomer_name(orders.getCustomer_name());
//        varOrder.setCustomer_lastname(orders.getCustomer_lastname());
//        varOrder.setEmail(orders.getEmail());
//        varOrder.setPhone(orders.getPhone());
//        varOrder.setEquipment(orders.getEquipment());
//        varOrder.setNo_serie(orders.getNo_serie());
//        varOrder.setBrand(orders.getBrand());
//        varOrder.setMalfunction(orders.getMalfunction());
//        varOrder.setAccesories(orders.getAccesories());
//        varOrder.setImage(orders.getImage());
//        varOrder.setUrl(orders.getUrl());
        varOrder.setNotes(orders.getNotes());
//        varOrder.setIssues(orders.getIssues());
//        varOrder.setSolution(orders.getSolution());
        varOrder.setStatusorders(Statusorders.valueOf(String.valueOf(orders.getStatusorders())));
//        varOrder.setSubtotal(orders.getSubtotal());
//        varOrder.setTax(orders.getTax());
//        varOrder.setTotal(orders.getTotal());
//        varOrder.setLabor(orders.getLabor());
//        varOrder.setSpare1(orders.getSpare1());
//        varOrder.setCost1(orders.getCost1());
//        varOrder.setSpare2(orders.getSpare2());
//        varOrder.setCost2(orders.getCost2());
//        varOrder.setSpare3(orders.getSpare3());
//        varOrder.setCost3(orders.getCost3());
//        varOrder.setSpare4(orders.getSpare4());
//        varOrder.setCost4(orders.getCost4());
//        varOrder.setSpare5(orders.getSpare5());
//        varOrder.setCost5(orders.getCost5());
//        varOrder.setSpare_parts_cost(orders.getSpare_parts_cost());
//        varOrder.setDelivery_date(orders.getDelivery_date());
//        varOrder.setTech_name(orders.getTech_name());
        varOrder.setContact(orders.isContact());
//            varOrder.setStatus(orders.isStatus());
//            varOrder.setCreateDate(orders.getCreateDate());
        Order updatedOrder = orderSalespersonRepository.save(varOrder);
//        return OrdersMapper.mapToDtoOrdersAll2(updatedOrder);
//        OrdersMapper.mapToDtoOrdersAll2(updatedOrder);
        return StandardResponse.getResponseEntity(String.valueOf(updatedOrder),HttpStatus.OK);
//        return new ResponseEntity<>(new StandardResponse("200", "Done",updatedOrder), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateOrderStatus(UpdateStatusOrders orders){
        Order varOrder = orderSalespersonRepository.findById(orders.getId())
                .orElseThrow(() -> new NotFoundException("OrderStatus Not found with id = " + orders.getId()));

        log.info("OrderStatus Seller Updated");
        varOrder.setStatusorders(Statusorders.valueOf(String.valueOf(orders.getStatusorders())));
        Order updatedOrder = orderSalespersonRepository.save(varOrder);
        return StandardResponse.getResponseEntity(String.valueOf(updatedOrder),HttpStatus.OK);
//        return new ResponseEntity<>(new StandardResponse("200", "Done",updatedOrder), HttpStatus.OK);
    }

//    @Override
//    public List<Order> findByStatus(boolean b) {
//        log.info("Orders Founds by status active");
//        return orderSellerRepository.findByStatusOrderByIdDesc(b);
//    }
//
//    @Override
//    public ResponseEntity<List<Order>> findByStatusAndType(boolean b, String StatusOrders)throws NotFoundException {
//        List<Order> typeOrders = orderSellerRepository.findByStatusAndStatusOrdersOrderByIdDesc(true, StatusOrders);
//
//        if(typeOrders.isEmpty()){
//            log.info("Orders Not Founds by status and type");
//            return new ResponseEntity<>(typeOrders, HttpStatus.NOT_FOUND);
//        }else {
//            log.info("Orders Founds by status and type");
//            return new ResponseEntity<>(typeOrders, HttpStatus.OK);
//        }
//    }

//    @Override
//    public OrderDTO save(OrderDTO ordersDTO) {
//        log.info("Order Added");
//        // Convert UserDto into User JPA Entity
//        Order orders = OrdersMapper.mapToOrders(ordersDTO);
//        Order savedOrders = orderSellerRepository.save(orders);
//
//        // Convert User JPA entity to UserDto
//        OrderDTO savedOrdersDto = OrdersMapper.mapToOrdersDTO(savedOrders);
//        return savedOrdersDto;
//    }

//    @Override
//    public void updateStatus(long orderId) {
//        Order varOrder = orderSellerRepository.findById(orderId)
//                .orElseThrow(() -> new NotFoundException("Order Not found with id = " + orderId));
//        log.info("Status Updated: id "+ orderId);
//        orderSellerRepository.updateStatus(orderId);
//    }

}
