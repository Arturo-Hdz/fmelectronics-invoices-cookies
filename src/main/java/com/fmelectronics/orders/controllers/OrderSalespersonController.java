package com.fmelectronics.orders.controllers;

import com.fmelectronics.orders.DTOs.OrderDTOSalesperson;
import com.fmelectronics.orders.DTOs.UpdateStatusOrders;
import com.fmelectronics.orders.payload.response.ResponseConstants;
import com.fmelectronics.orders.repositories.OrderSalespersonRepository;
import com.fmelectronics.orders.services.OrderSalespersonService;
import com.fmelectronics.orders.util.StandardResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author Arturo Hdez on 3/26/24;
 * @project back-orders
 */

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/seller/order")
public class OrderSalespersonController {

    @Autowired
    OrderSalespersonRepository orderSalespersonRepository;

    @Autowired
    OrderSalespersonService orderSalespersonService;

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('SELLER')")
    public ResponseEntity<?> updateOrder(@PathVariable("id") long orderId, @Valid @RequestBody OrderDTOSalesperson ordersDTOSalesperson)throws Exception{
        try {
            ordersDTOSalesperson.setId(orderId);
            orderSalespersonService.update(ordersDTOSalesperson);
            return new ResponseEntity<>("Order Disabled!", HttpStatus.OK);
//            return StandardResponse.getResponseEntity("Seller Order Updated!"),HttpStatus.OK);
//            return new ResponseEntity<>(new StandardResponse("200", "Done", orderSellerService.update(ordersDTOSeller)), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
//            return new ResponseEntity<>(new StandardResponse("500", "Error", orderSellerService.update(ordersDTOSeller)), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return StandardResponse.getResponseEntity(ResponseConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PatchMapping("/orderStatus/{id}")
    @PreAuthorize("hasRole('SELLER')")
    public ResponseEntity<?> updateOrderStatus(@PathVariable("id") long orderId, @Valid @RequestBody UpdateStatusOrders statusOrderDTO)throws Exception{
        try {
            statusOrderDTO.setId(orderId);
            orderSalespersonService.updateOrderStatus(statusOrderDTO);
            return new ResponseEntity<>("Seller Order Status Disabled!", HttpStatus.OK);
//            return new ResponseEntity<>(new StandardResponse("200", "Done", orderSellerService.updateOrderStatus(statusOrderDTO)), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
//            return new ResponseEntity<>(new StandardResponse("500", "Error", orderSellerService.updateOrderStatus(statusOrderDTO)), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return StandardResponse.getResponseEntity(ResponseConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    @PutMapping("/{id}")
//    @PreAuthorize("hasRole('SELLER')")
//    public ResponseEntity<?> updateOrder(@PathVariable("id") long orderId, @Valid @RequestBody OrderDTO ordersDTO)throws Exception{
//        try {
//            return new ResponseEntity<>(new StandardResponse("200", "Done", orderSellerService.update(ordersDTO)), HttpStatus.OK);
//        }catch (Exception e){
//            return new ResponseEntity<>(new StandardResponse("500", "Error", "INTERNAL ERROR"), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//    }

//    @GetMapping("/status")
//    public ResponseEntity<?> listProductsTrueStatus(){
//        try {
//            List<Order> orders = orderSellerService.findByStatus(true);
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
//        return orderSellerService.findByStatusAndType(true, type);
//    }
//
//    @GetMapping("/search")
//    public ResponseEntity<?> searchOrders(@RequestParam(name = "keyword", defaultValue = "") String keyword){
//        try {
//            return new ResponseEntity<>(orderSellerRepository.searchOrders(true, keyword),null, HttpStatus.OK);
//        } catch (Exception e){
//            return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
//        }
//    }

}
