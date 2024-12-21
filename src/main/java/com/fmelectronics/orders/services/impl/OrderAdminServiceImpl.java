package com.fmelectronics.orders.services.impl;

import com.fmelectronics.orders.DTOs.OrderDTO;
import com.fmelectronics.orders.DTOs.OrderDTOAll;
import com.fmelectronics.orders.DTOs.OrderDTOImage;
import com.fmelectronics.orders.DTOs.UpdateStatusOrders;
import com.fmelectronics.orders.models.Order;
import com.fmelectronics.orders.models.enums.Statusorders;
import com.fmelectronics.orders.exceptions.NotFoundException;
import com.fmelectronics.orders.mappers.ImageMapper;
import com.fmelectronics.orders.mappers.OrdersMapper;
import com.fmelectronics.orders.payload.response.ResponseConstants;
import com.fmelectronics.orders.repositories.OrderAdminRepository;
import com.fmelectronics.orders.security.jwt.JwtUtils;
import com.fmelectronics.orders.services.OrderAdminService;
import com.fmelectronics.orders.services.UploadService;
import com.fmelectronics.orders.util.StandardResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author Arturo Hdez on 10/18/24;
 * @project fmelectronics-orders
 */

@Service
@Slf4j
public class OrderAdminServiceImpl implements OrderAdminService {

    @Autowired
    OrderAdminRepository orderAdminRepository;

    @Autowired
    private JwtUtils jwtUtils;
    String url = "http://localhost:8086/upload/";

    @Autowired
    UploadService uploadService;

    // DTOimages
    @Override
    public OrderDTOImage save(OrderDTOImage dtoImage, MultipartFile file) throws IOException {
        String name = uploadService.saveUpload(file);
        log.info("Image saved!");
        dtoImage.setImage(name);
        Order dtoImage2 = ImageMapper.mapToOrdersDTOImage(dtoImage);
        orderAdminRepository.save(dtoImage2);
        return dtoImage;
    }

    @Override
    public List<OrderDTOImage> getImages() {
        return List.of();
    }

    @Override
    public OrderDTOImage get(Long id) {
        return null;
    }

//    Order savedOrders = OrdersMapper.mapToOrdersDTO(ordersDTO);
////        savedOrders =
//                orderAdminRepository.save(savedOrders);

//    @Override
//    public List<OrderDTOImage> getImages() {
////        OrderDTOImage dtoImage ImageMapper.mapToOrdersDTOImage(dtoImage)
//        List<OrderDTOImage> dtoImages = orderAdminRepository.findAll();
//        dtoImages = dtoImages.stream().map(orderDTOImage -> {orderDTOImage.setImage(url + orderDTOImage.getImage());
//            return orderDTOImage;
//        }).collect(Collectors.toList());
//        return dtoImages;
//    }
//
//    @Override
//    public OrderDTOImage get(Long id) {
//        OrderDTOImage dtoImage = orderAdminRepository.findById(id).get();
//        dtoImage.setImage(url+ dtoImage.getImage());
//        return dtoImage;
//    }

    //-----------
    @Override
    public List<Order> findByStatus(boolean b) {
        log.info("Orders Founds by status active");
        return orderAdminRepository.findByStatusOrderByIdDesc(b);
    }

    @Override
    public Order getOrderById(Integer id) {
        log.info("Orders Founds by status active By Id");
        return orderAdminRepository.OrderById(id);
    }

//    @Override
//    public List<Order> findByStatus2(boolean b) {
////        try {
//                List<Order> orders = orderAdminRepository.findByStatusOrderByIdDesc(true);
//                if(orders.isEmpty()){
//                    log.info("Not Founds Orders by status true");
//                    return new ResponseEntity(new StandardResponse("400", "Error", orders), HttpStatus.BAD_REQUEST);
////                    return new ResponseEntity<>(orders, HttpStatus.NO_CONTENT);
//                }else {
//                    log.info("Orders Founds by status true");
//                    return new ResponseEntity(new StandardResponse("200", "Done",orders), HttpStatus.OK);
////                    return new ResponseEntity<>(orders, HttpStatus.OK);
//                }
////        }catch (Exception exception){
////            exception.printStackTrace();
////        }
////        return new ResponseEntity(new StandardResponse("500", "Error", "INTERNAL SERVER ERROR: LIST ORDERS STATUS 'TRUE'"), HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    @Override
    public ResponseEntity<List<Order>> findByStatusOrders(boolean b, Statusorders statusorders)throws NotFoundException {
//        List<Order> statusOrders2 = orderAdminRepository.findByStatusAndStatusOrdersOrderByIdDesc(true, statusOrders);
        List<Order> statusOrders2 = orderAdminRepository.searchOrdersByStatusorders(true, Statusorders.valueOf(statusorders.SOString));

        if(statusOrders2.isEmpty()){
            log.info("Not Founds Orders by status and statusorders");
            return new ResponseEntity<>(statusOrders2,HttpStatus.BAD_REQUEST);
//            return new ResponseEntity(new StandardResponse("400", "Error", statusOrders2), HttpStatus.BAD_REQUEST);
        }else {
            log.info("Founds Orders by status and statusorders");
            return new ResponseEntity<>(statusOrders2,HttpStatus.OK);
//            return new ResponseEntity(new StandardResponse("200", "Done",statusOrders2), HttpStatus.OK);
        }
    }
//
//    @Override
//    public ResponseEntity<List<Order>> searchOrders(String keyword)throws NotFoundException{
//        List<Order> searchOrders = orderAdminRepository.searchOrders(keyword);
//        if(searchOrders.isEmpty()){
//            log.info("Not Founds Orders by status and type");
//            return new ResponseEntity<>(searchOrders, HttpStatus.NOT_FOUND);
//        }else {
//            log.info("Founds Orders by status and keyword");
//            return new ResponseEntity<>(searchOrders, HttpStatus.OK);
//        }
//    }

//    @Override
//    public ResponseEntity<?> findByDateBetweenOrderByDateDesc(LocalDate date1, LocalDate date2) {
//        System.out.println(date1);
//        System.out.println(date2);
//        if(date1==null || date2==null){
//            log.info("Orders Not Founds by Date Desc");
//            List<Order> optional1 = orderAdminRepository.findByDateBetweenOrderByDateDesc(date1, date2);
//            return new ResponseEntity<>(new StandardResponse("400", "Error", "Start Date or End Date is null"+ optional1), HttpStatus.NOT_FOUND);
//        }else {
//            log.info("Orders Founds by Date Desc");
//            List<Order> optional = orderAdminRepository.findByDateBetweenOrderByDateDesc(date1, date2);
//            return new ResponseEntity<>(new StandardResponse("200", "Done", optional), HttpStatus.OK);
//        }
//    }

    @Override
    public OrderDTO save(OrderDTO ordersDTO) {
        log.info("Order Added");
        // Convert Order JPA entity to OrderDto
        Order savedOrders = OrdersMapper.mapToOrdersDTO(ordersDTO);
//        savedOrders =
                orderAdminRepository.save(savedOrders);

        // Convert OrderDto into Order JPA Entity
//        OrderDTO savedOrdersDto = OrdersMapper.mapToOrdersDTO(savedOrders);
        return null;
//        return OrdersMapper.mapToOrders(orders);
//        OrdersMapper.mapToOrders(savedOrders);
//        return new ResponseEntity<?>(savedOrders,HttpStatus.OK);
//        return new ResponseEntity<>(new StandardResponse("200", "Done",savedOrders), HttpStatus.OK);
    }

    @Override
    public Order update(Order orders){
        Order varOrder = orderAdminRepository.findById(orders.getId())
                .orElseThrow(() -> new NotFoundException("Order Not found with id = " + orders.getId()));

        log.info("Order Admin Updated");
            varOrder.setNoorder(orders.getNoorder());
            varOrder.setDate(orders.getDate());
            varOrder.setCustomer_name(orders.getCustomer_name());
            varOrder.setCustomer_lastname(orders.getCustomer_lastname());
            varOrder.setEmail(orders.getEmail());
            varOrder.setPhone(orders.getPhone());
            varOrder.setEquipment(orders.getEquipment());
            varOrder.setNo_serie(orders.getNo_serie());
            varOrder.setBrand(orders.getBrand());
            varOrder.setMalfunction(orders.getMalfunction());
            varOrder.setAccessories(orders.getAccessories());
            varOrder.setImage(orders.getImage());
            varOrder.setUrl(orders.getUrl());
            varOrder.setNotes(orders.getNotes());
            varOrder.setIssues(orders.getIssues());
            varOrder.setSolution(orders.getSolution());
            varOrder.setStatusorders(Statusorders.valueOf(String.valueOf(orders.getStatusorders())));
            varOrder.setSubtotal(orders.getSubtotal());
            varOrder.setTax(orders.getTax());
            varOrder.setTotal(orders.getTotal());
            varOrder.setLabor(orders.getLabor());
            varOrder.setSpare1(orders.getSpare1());
            varOrder.setCost1(orders.getCost1());
            varOrder.setSpare2(orders.getSpare2());
            varOrder.setCost2(orders.getCost2());
            varOrder.setSpare3(orders.getSpare3());
            varOrder.setCost3(orders.getCost3());
            varOrder.setSpare4(orders.getSpare4());
            varOrder.setCost4(orders.getCost4());
            varOrder.setSpare5(orders.getSpare5());
            varOrder.setCost5(orders.getCost5());
            varOrder.setSpare_parts_cost(orders.getSpare_parts_cost());
            varOrder.setDelivery_date(orders.getDelivery_date());
            varOrder.setTech_name(orders.getTech_name());
            varOrder.setContact(orders.isContact());
//            varOrder.setStatus(orders.isStatus());
//            varOrder.setCreateDate(orders.getCreateDate());
        Order updatedOrder = orderAdminRepository.save(varOrder);
//        return OrdersMapper.mapToOrders(updatedOrder);
        return updatedOrder;
//        return StandardResponse.getResponseEntity(updatedOrder, HttpStatus.OK);
//        return new ResponseEntity<?>(updatedOrder,HttpStatus.OK);

//        OrdersMapper.mapToDtoOrdersAll2(updatedOrder);
//        return new ResponseEntity<>(new StandardResponse("200", "Done",updatedOrder), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updatedStatus(long orderId) {
        try {
            Order varOrder = orderAdminRepository.findById(orderId)
                    .orElseThrow(() -> new NotFoundException("Order Not found with id = " + orderId));

            log.info("Status Updated: id "+ orderId);
            orderAdminRepository.updatedStatus(orderId);
            return StandardResponse.getResponseEntity("Status Updated!",HttpStatus.OK);
//            return new ResponseEntity(new StandardResponse("200", "Done", "Status Updated! " + orderId), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return StandardResponse.getResponseEntity(ResponseConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<?> updateOrderStatus(UpdateStatusOrders orders){
        Order varOrder = orderAdminRepository.findById(orders.getId())
                .orElseThrow(() -> new NotFoundException("OrderStatus Not found with id = " + orders.getId()));

        log.info("OrderStatus Admin Updated");
        varOrder.setStatusorders(Statusorders.valueOf(String.valueOf(orders.getStatusorders())));
        Order updatedOrder = orderAdminRepository.save(varOrder);
        return StandardResponse.getResponseEntity("Order Status Admin Updated!",HttpStatus.OK);
//        return new ResponseEntity<>(new StandardResponse("200", "Done",updatedOrder), HttpStatus.OK);
    }

}
