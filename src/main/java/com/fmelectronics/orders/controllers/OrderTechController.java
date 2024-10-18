package com.fmelectronics.orders.controllers;

import com.fmelectronics.orders.DTOs.OrderDTOTech;
import com.fmelectronics.orders.DTOs.UpdateStatusOrders;
import com.fmelectronics.orders.models.Order;
import com.fmelectronics.orders.payload.response.ResponseConstants;
import com.fmelectronics.orders.repositories.OrderTechRepository;
import com.fmelectronics.orders.services.OrderTechService;
import com.fmelectronics.orders.util.StandardResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Arturo Hdez on 3/26/24;
 * @project back-orders
 */

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/tech/order")
public class OrderTechController {

    @Autowired
    OrderTechRepository orderTechRepository;

    @Autowired
    OrderTechService orderTechService;

    @GetMapping("/listOrderTech")
    public ResponseEntity<List<Order>> listNewRoles(){
        List<Order> newOrder = orderTechRepository.listOrderTech();
        if(newOrder.isEmpty()){
            return new ResponseEntity<>(newOrder, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(newOrder, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('TECHNICIAN')")
    public ResponseEntity<?> updateOrder(@PathVariable("id") long orderId, @Valid @RequestBody OrderDTOTech ordersDTOTech)throws Exception{
        try {
            ordersDTOTech.setId(orderId);
            orderTechService.update(ordersDTOTech);
            return new ResponseEntity<>("Tech Order Updated!", HttpStatus.OK);
//            return new ResponseEntity<>(new StandardResponse("200", "Done", orderTechService.update(ordersDTOTech)), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
//            return new ResponseEntity<>(new StandardResponse("500", "Error", orderTechService.update(ordersDTOTech)), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return StandardResponse.getResponseEntity(ResponseConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PatchMapping("/orderStatus/{id}")
    @PreAuthorize("hasRole('TECHNICIAN')")
    public ResponseEntity<?> updateOrderStatus(@PathVariable("id") long orderId, @Valid @RequestBody UpdateStatusOrders statusOrderDTO)throws Exception{
        try {
            statusOrderDTO.setId(orderId);
            orderTechService.updateOrderStatus(statusOrderDTO);
            return new ResponseEntity<>("Tech Order Status Updated!", HttpStatus.OK);
//            return new ResponseEntity<>(new StandardResponse("200", "Done", orderTechService.updateOrderStatus(statusOrderDTO)), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
//            return new ResponseEntity<>(new StandardResponse("500", "Error", orderTechService.updateOrderStatus(statusOrderDTO)), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return StandardResponse.getResponseEntity(ResponseConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<?> updateOrder(@PathVariable("id") long orderId, @Valid @RequestBody OrderDTO ordersDTO)throws Exception{
////        catalogsDTO.setId(catalogId);
//        try {
//            OrderDTO updatedOrders = orderTechService.update(ordersDTO);
//            return new ResponseEntity<>(ordersDTO, HttpStatus.OK);
//        }catch (Exception e){
//            return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
//        }
//    }

//    @PutMapping("/{id}")
//    @PreAuthorize("hasRole('TECHNICIAN')")
//    public ResponseEntity<?> updateOrder(@PathVariable("id") long orderId, @Valid @RequestBody OrderDTO ordersDTO)throws Exception{
//        try {
//            return new ResponseEntity<>(new StandardResponse("200", "Done", orderTechService.update(ordersDTO)), HttpStatus.OK);
//        }catch (Exception e){
//            return new ResponseEntity<>(new StandardResponse("500", "Error", "INTERNAL ERROR"), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//    }

//    @GetMapping("/status")
//    public ResponseEntity<?> listProductsTrueStatus(){
//        try {
//            List<Order> orders = orderTechService.findByStatus(true);
//            if(orders.isEmpty()){
//                return new ResponseEntity<>(orders, HttpStatus.NO_CONTENT);
//            }
//            return new ResponseEntity<>(orders, HttpStatus.OK);
//        } catch (Exception e){
//               return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
//        }
//    }
//
//    @GetMapping("/status/{type}")
//    public ResponseEntity<List<Order>> listOrderTrueStatus2(@PathVariable("type") String type)throws NotFoundException {
//        return orderTechService.findByStatusAndType(true, type);
//    }
//
//    @GetMapping("/search")
//    public ResponseEntity<?> searchOrders(@RequestParam(name = "keyword", defaultValue = "") String keyword){
//        try {
//            return new ResponseEntity<>(orderTechRepository.searchOrders(true, keyword),null, HttpStatus.OK);
//        } catch (Exception e){
//            return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
//        }
//    }

}
