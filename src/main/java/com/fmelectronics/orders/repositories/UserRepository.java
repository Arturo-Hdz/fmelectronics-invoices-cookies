package com.fmelectronics.orders.repositories;

import com.fmelectronics.orders.models.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);

  @Query("UPDATE User u set u.status=false Where u.id = ?1")
  @Transactional
  @Modifying
  void updatedStatus(long userId);
}
