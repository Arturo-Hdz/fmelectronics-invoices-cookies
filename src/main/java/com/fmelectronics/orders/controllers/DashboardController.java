package com.fmelectronics.orders.controllers;

import com.fmelectronics.orders.models.enums.Statusorders;
import com.fmelectronics.orders.exceptions.NotFoundException;
import com.fmelectronics.orders.payload.response.ResponseConstants;
import com.fmelectronics.orders.services.DashboardService;
import com.fmelectronics.orders.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static com.fmelectronics.orders.models.enums.Statusorders.NEW_ORDER;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/details")
    public ResponseEntity<Map<String,Object>> getCount(){
        return dashboardService.getCount();
    }

    @GetMapping("/count")
    public ResponseEntity<?> listProductsTrueStatus(){
        try {
//            orderAdminService.findByStatus(true);
//            List<Order> tutorials = new ArrayList<Order>();
            return dashboardService.countByName("Arturo", true);

//            return new ResponseEntity<>(tutorials, HttpStatus.OK);
//            return StandardResponse.getResponseEntity("done",HttpStatus.OK);
//            return new ResponseEntity(new StandardResponse("200", "Done", dashboardService.countByName("Arturo", true)), HttpStatus.OK);
//            return new ResponseEntity<>(orders, HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
//            return ResponseEntity.badRequest().body(new MessageResponse("INTERNAL SERVER ERROR!"));
//            return new ResponseEntity(new StandardResponse("500", "Error", "INTERNAL ERROR"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return StandardResponse.getResponseEntity(ResponseConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @GetMapping("/countstatusorders")
    public ResponseEntity<?> StatusAndStatusOrder(){
        try {
//            orderAdminService.findByStatus(true);
//            List<Order> tutorials = new ArrayList<Order>();
            return dashboardService.countByStatusOrders(true, NEW_ORDER);
//            return new ResponseEntity<>(tutorials, HttpStatus.OK);
            //            return StandardResponse.getResponseEntity("done",HttpStatus.OK);
//            return new ResponseEntity(new StandardResponse("200", "Done", dashboardService.countByStatusOrders(true, NEW_ORDER)), HttpStatus.OK);
//            return new ResponseEntity<>(orders, HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
//            return ResponseEntity.badRequest().body(new MessageResponse("INTERNAL SERVER ERROR!"));
//            return new ResponseEntity(new StandardResponse("500", "Error", dashboardService.countByStatusOrders(true,NEW_ORDER)), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return StandardResponse.getResponseEntity(ResponseConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/count/status/{statusOrders}")
//    @PreAuthorize("hasRole('ADMIN') or hasRole('SELLER') or hasRole('TECHNICIAN')")
    public ResponseEntity<?> listOrderTrueStatus2(@PathVariable("statusorders") Statusorders statusorders)throws NotFoundException {
        try {
            return dashboardService.countByStatusOrders2(true, statusorders);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
