package com.fmelectronics.orders.services;

import com.fmelectronics.orders.DTOs.OrderDTOTech;
import com.fmelectronics.orders.DTOs.UpdateStatusOrders;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

/**
 * @author Arturo Hdez on 3/26/24;
 * @project back-orders
 */
public interface OrderTechService {

//    List<Order> findByStatus(boolean b);
//    ResponseEntity<List<Order>> findByStatusAndType(boolean b, String type);
//    OrderDTO save(OrderDTO orderDTO);
//    SendCatalogsDTO sendCatalogs(SendCatalogsDTO sendCatalogsDTO) throws MessagingException;
    //    Catalogs update(Catalogs varCatalog);
//    OrderDTO update(OrderDTO orderDTO);
//    void updateStatus(long orderId);

    ResponseEntity<?> update(@Valid OrderDTOTech orderDTOTech);
    ResponseEntity<?> updateOrderStatus(UpdateStatusOrders statusOrderDTO);
}
