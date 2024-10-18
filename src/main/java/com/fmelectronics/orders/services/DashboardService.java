package com.fmelectronics.orders.services;

import com.fmelectronics.orders.models.enums.Statusorders;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface DashboardService {

    ResponseEntity<Map<String,Object>> getCount();
    ResponseEntity<?> countByName(String name, boolean b);
    ResponseEntity<?> countByStatusOrders(boolean b, Statusorders statusorders);

    ResponseEntity<?> countByStatusOrders2(boolean b, Statusorders statusorders);

}
