package com.fmelectronics.orders.services;

import com.fmelectronics.orders.models.User;
import org.springframework.http.ResponseEntity;

/**
 * @author Arturo Hdez on 6/5/24;
 * @project fm-electronics-orders-roles
 */
public interface UserService {
    ResponseEntity<?> updatedStatus(long orderId);
    User update(User user);

//    User loadUserByUsername(String username);
//    ResponseEntity<?> update(User user);
}
