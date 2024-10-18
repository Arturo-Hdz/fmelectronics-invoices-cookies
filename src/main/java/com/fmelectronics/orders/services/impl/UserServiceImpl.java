package com.fmelectronics.orders.services.impl;

import com.fmelectronics.orders.models.User;
import com.fmelectronics.orders.exceptions.NotFoundException;
import com.fmelectronics.orders.repositories.RoleRepository;
import com.fmelectronics.orders.repositories.UserRepository;
import com.fmelectronics.orders.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Arturo Hdez on 6/5/24;
 * @project fm-electronics-orders-roles
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Override
    public User update(User user){
        User varUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new NotFoundException("User Not found with id= " + user.getId()));

//        Role varRole = roleRepository.findById(user.getRoles())
//                .orElseThrow(() -> new NotFoundException("Role Not found with id = " + user.getRoles()));

//        Role roles = roleRepository.findById(user.getRoles())
//                .orElseThrow(() -> new NotFoundException("Role not found with id= " + user.getRoles()));
//        if (!userRepository.existsById(userId)) {
//            throw new NotFoundException("Not found User with id = " + userId);
//        }
//        if (!roleRepository.existsById(roleId)) {
//            throw new NotFoundException("Not found Role with id = " + roleId);
//        }
//        Role roles = roleRepository.findById(user.getRoles())
//                .orElseThrow(() -> new NotFoundException("Role not found with id " + role_id));
//        log.info("Roles var1111 " + roles.getName());
//        roles1.add(roles);
//        user.setRoles(roles1);

        log.info("User Rol Updated");
//        log.info("id user"+ user.getId());
//        log.info("pass"+ user.getPassword());
        varUser.setAddress(user.getAddress());
        varUser.setEmail(user.getEmail());
        varUser.setGender(user.getGender());
        varUser.setPhone_number(user.getPhone_number());
        varUser.setPassword(encoder.encode(user.getPassword()));
        varUser.setUsername(user.getUsername());
        varUser.setStatus(user.isStatus());
        varUser.setGender(user.getGender());
        varUser.setRoles(user.getRoles());
//        varOrder.setStatusOrders(StatusOrders.valueOf(String.valueOf(orders.getStatusOrders())));
//        User updated_user = userRepository.save(varUser);
//        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
//        return updated_user;
        return userRepository.save(varUser);

//        return new ResponseEntity<>(new StandardResponse("200", "Done",updatedUser), HttpStatus.OK);
    }

//    @Override
//    public User loadUserByUsername(String username) throws NotFoundException{
//        User user = this.userRepository.findByUsername(username);
//        if(user == null){
//            throw new NotFoundException("user not found");
//        }
//        return user;
//    }

    @Override
    public ResponseEntity<?> updatedStatus(long userId) {
        try {
            User varUser = userRepository.findById(userId)
                    .orElseThrow(() -> new NotFoundException("User Not found with id= " + userId));

            log.info("Status Updated: id "+ userId);
            userRepository.updatedStatus(userId);
            return new ResponseEntity<>("Status Updated: id= "+ userId, HttpStatus.OK);
//            return new ResponseEntity(new StandardResponse("200", "Done", "User Updated! " + userId), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
//        return new ResponseEntity<>(new StandardResponse("500", "Error", "User Internal Error"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
