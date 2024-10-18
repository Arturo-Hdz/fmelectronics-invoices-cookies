package com.fmelectronics.orders.services.impl;

import com.fmelectronics.orders.models.enums.Statusorders;
import com.fmelectronics.orders.exceptions.NotFoundException;
import com.fmelectronics.orders.repositories.OrderAdminRepository;
import com.fmelectronics.orders.repositories.UserRepository;
import com.fmelectronics.orders.services.DashboardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class DashboardServiceImpl implements DashboardService{

    @Autowired
    private OrderAdminRepository orderAdminRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<Map<String, Object>> getCount() {
        Map<String,Object> map = new HashMap<>();
            map.put("order", orderAdminRepository.count());
            map.put("user", userRepository.count());
//            map.put("order", orderAdminRepository.countByName(name));
//            map.put("order", orderAdminRepository.findByStatusOrdersOrderByIdDesc(true, String.valueOf(StatusOrders.NEW_ORDER)));
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> countByName(String name, boolean b) {
         long countOrders = orderAdminRepository.countByName(name, b);
            return new ResponseEntity<>(countOrders, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> countByStatusOrders(boolean b, Statusorders Statusorders) {
        long countOrders = orderAdminRepository.countByStatusOrders(b, Statusorders);
        log.info("Founds Status Orders by status true" + countOrders);
        return new ResponseEntity<>(countOrders, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> countByStatusOrders2(boolean b, Statusorders statusorders)throws NotFoundException {
        long status_orders2 = orderAdminRepository.countByStatusOrders2(true, Statusorders.valueOf(statusorders.SOString));
        log.info("Founds Status Orders by status true" + status_orders2);
        return new ResponseEntity<>(status_orders2, HttpStatus.OK);

//        if(statusOrders2.isEmpty()){
//            log.info("Orders Not Founds by status and statusorders");
//            return new ResponseEntity<>(statusOrders2,HttpStatus.BAD_REQUEST);
//        }else {
//            log.info("Founds Orders by status and statusorders");
//            return new ResponseEntity<>(statusOrders2,HttpStatus.OK);
////            return new ResponseEntity(new StandardResponse("200", "Done",statusOrders2), HttpStatus.OK);
//        }
    }
}
