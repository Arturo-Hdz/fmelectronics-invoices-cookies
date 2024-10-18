package com.fmelectronics.orders.services;

import com.fmelectronics.orders.DTOs.OrderDTOSalesperson;
import com.fmelectronics.orders.DTOs.UpdateStatusOrders;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

/**
 * @author Arturo Hdez on 3/26/24;
 * @project back-orders
 */
public interface OrderSalespersonService {

//    List<Order> findByStatus(boolean b);
//    ResponseEntity<List<Order>> findByStatusAndType(boolean b, String StatusOrders);
//    OrderDTO save(OrderDTO orderDTO);
//    SendCatalogsDTO sendCatalogs(SendCatalogsDTO sendCatalogsDTO) throws MessagingException;
    //    Catalogs update(Catalogs varCatalog);

//    ResponseEntity<?> update(@Valid UpdateStatusOrders suso);
//    OrderDTOSeller update(@Valid OrderDTOSeller  orderDTOSeller);
//    void updateStatus(long orderId);

    ResponseEntity<?> update(@Valid OrderDTOSalesperson orderDTOSalesperson);
    ResponseEntity<?> updateOrderStatus(UpdateStatusOrders statusOrderDTO);
}
