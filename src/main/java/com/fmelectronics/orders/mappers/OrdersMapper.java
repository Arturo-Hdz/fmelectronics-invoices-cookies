package com.fmelectronics.orders.mappers;

import com.fmelectronics.orders.DTOs.OrderDTO;
import com.fmelectronics.orders.models.Order;

public class OrdersMapper {

    // Convert Order JPA Entity into OrderDto
    // JPA - OrderDto orders All users
    public static Order mapToOrdersDTO(OrderDTO orderDTO){
        Order order = new Order();
        order.setId(orderDTO.getId());
        order.setNoorder(orderDTO.getNoorder());
        order.setDate(orderDTO.getDate());
        order.setCustomer_name(orderDTO.getCustomer_name());
        order.setCustomer_lastname(orderDTO.getCustomer_lastname());
        order.setEmail(orderDTO.getEmail());
        order.setPhone(orderDTO.getPhone());
        order.setEquipment(orderDTO.getEquipment());
        order.setNo_serie(orderDTO.getNo_serie());
        order.setBrand(orderDTO.getBrand());
        order.setMalfunction(orderDTO.getMalfunction());
        order.setAccessories(orderDTO.getAccessories());
        order.setImage(orderDTO.getImages());
        order.setUrl(orderDTO.getUrl());
        order.setNotes(orderDTO.getNotes());
        order.setStatusorders(orderDTO.getStatusorders());
        order.setTech_name(orderDTO.getTech_name());
        return order;
    }

////    JPA - OrderDto All Admin
//    public static OrderDTOAll mapToDtoOrdersAll2(Order order) {
//        OrderDTOAll orderdto = new OrderDTOAll(
//                order.getId(), order.getNo_order(), order.getDate(), order.getCustomer_name(),
//                order.getCustomer_lastname(), order.getEmail(), order.getPhone(), order.getEquipment(),
//                order.getNo_serie(), order.getBrand(), order.getMalfunction(), order.getAccesories(),
//                order.getImage(), order.getUrl(), order.getNotes(), order.getIssues(),
//                order.getSolution(), order.getStatusOrders(), order.getSubtotal(), order.getTax(),
//                order.getTotal(), order.getLabor(), order.getSpare1(), order.getCost1(),
//                order.getSpare2(), order.getCost2(), order.getSpare3(), order.getCost3(),
//                order.getSpare4(), order.getCost4(), order.getSpare5(), order.getCost5(),
//                order.getSpare_parts_cost(), order.getDelivery_date(), order.getTech_name(),
//                order.isContact(), order.isStatus()
//        );
//        return orderdto;
//    }

    // Convert OrderDto into Order JPA Entity
    // OrderDto orders - JPA All users
    public static OrderDTO mapToOrders(Order order){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setNoorder(order.getNoorder());
        orderDTO.setDate(order.getDate());
        orderDTO.setCustomer_name(order.getCustomer_name());
        orderDTO.setCustomer_lastname(order.getCustomer_lastname());
        orderDTO.setEmail(order.getEmail());
        orderDTO.setPhone(order.getPhone());
        orderDTO.setEquipment(order.getEquipment());
        orderDTO.setNo_serie(order.getNo_serie());
        orderDTO.setBrand(order.getBrand());
        orderDTO.setMalfunction(order.getMalfunction());
        orderDTO.setAccessories(order.getAccessories());
        orderDTO.setImages(order.getImage());
        orderDTO.setUrl(order.getUrl());
        orderDTO.setNotes(order.getNotes());
        orderDTO.setStatusorders(order.getStatusorders());
        orderDTO.setTech_name(order.getTech_name());
        return orderDTO;
    }

//    // OrderDto orders - JPA Admin
//    public static Order mapToOrdersAll(OrderDTOAll orderDTOAll) {
//        Order order = new Order(
//                orderDTOAll.getId(), orderDTOAll.getNo_order(), orderDTOAll.getDate(), orderDTOAll.getCustomer_name(),
//                orderDTOAll.getCustomer_lastname(), orderDTOAll.getEmail(), orderDTOAll.getPhone(), orderDTOAll.getEquipment(),
//                orderDTOAll.getNo_serie(), orderDTOAll.getBrand(), orderDTOAll.getMalfunction(), orderDTOAll.getAccesories(),
//                orderDTOAll.getImage(), orderDTOAll.getUrl(), orderDTOAll.getNotes(), orderDTOAll.getIssues(),
//                orderDTOAll.getSolution(), orderDTOAll.getStatusOrders(), orderDTOAll.getSubtotal(), orderDTOAll.getTax(),
//                orderDTOAll.getTotal(), orderDTOAll.getLabor(), orderDTOAll.getSpare1(), orderDTOAll.getCost1(),
//                orderDTOAll.getSpare2(), orderDTOAll.getCost2(), orderDTOAll.getSpare3(), orderDTOAll.getCost3(),
//                orderDTOAll.getSpare4(), orderDTOAll.getCost4(), orderDTOAll.getSpare5(), orderDTOAll.getCost5(),
//                orderDTOAll.getSpare_parts_cost(), orderDTOAll.getDelivery_date(), orderDTOAll.getTech_name(),
//                orderDTOAll.isContact(), orderDTOAll.isStatus()
//        );
//        return order;
//    }

}
