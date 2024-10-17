package com.fmelectronics.orders.repository;

import com.fmelectronics.orders.models.enums.ERole;
import com.fmelectronics.orders.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
