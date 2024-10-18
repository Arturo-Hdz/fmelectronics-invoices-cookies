package com.fmelectronics.orders.services;

import com.fmelectronics.orders.DTOs.OrderDTO;
import com.fmelectronics.orders.DTOs.OrderDTOImage;
import com.fmelectronics.orders.DTOs.UpdateStatusOrders;
import com.fmelectronics.orders.models.Order;
import com.fmelectronics.orders.models.enums.Statusorders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author Arturo Hdez on 10/18/24;
 * @project fmelectronics-orders
 */
public interface OrderAdminService {

    List<Order> findByStatus(boolean b);
    ResponseEntity<List<Order>> findByStatusOrders(boolean b, Statusorders statusOrders);
//    ResponseEntity<?> findByDateBetweenOrderByDateDesc(LocalDate date1, LocalDate date2);
    OrderDTO save(OrderDTO orderDTO);
//    SendCatalogsDTO sendCatalogs(SendCatalogsDTO sendCatalogsDTO) throws MessagingException;
    Order update(Order order);
//    ResponseEntity<?> update(OrderDTOAll orderDTO);
    ResponseEntity<?> updatedStatus(long orderId);
    ResponseEntity<?> updateOrderStatus(UpdateStatusOrders statusOrderDTO);
    Order getOrderById(Integer id);

    // images
    OrderDTOImage save(OrderDTOImage dtoImage, MultipartFile file) throws IOException;
    List<OrderDTOImage> getImages();
    OrderDTOImage get(Long id);

//    OrderDTOAll save(OrderDTOAll orderDTO, MultipartFile file) throws IOException;
}
