package com.fmelectronics.orders.mappers;

import com.fmelectronics.orders.DTOs.OrderDTOImage;
import com.fmelectronics.orders.models.Order;

public class ImageMapper {

    // Convert Order JPA Entity into OrderDto
    // JPA - OrderDto orders All users
    public static Order mapToOrdersDTOImage(OrderDTOImage dtoImage){
        Order order = new Order();
        order.setImage(dtoImage.getImage());
//        order.setUrl(dtoImage.getUrl());
        order.setNotes(dtoImage.getNotes());
        return order;
    }

    // Convert OrderDto into Order JPA Entity
    // OrderDto orders - JPA All users
    public static OrderDTOImage mapToOrders(Order order){
        OrderDTOImage dtoImage = new OrderDTOImage();
        dtoImage.setImage(order.getImage());
//        dtoImage.setUrl(order.getUrl());
        dtoImage.setNotes(order.getNotes());
        return dtoImage;
    }

}
