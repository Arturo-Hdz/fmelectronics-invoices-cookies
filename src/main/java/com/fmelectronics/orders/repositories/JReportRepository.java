package com.fmelectronics.orders.repositories;

import com.fmelectronics.orders.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Arturo Hdez on 5/17/24;
 * @project fm-electronics-orders-roles
 */
@Repository
public interface JReportRepository extends JpaRepository<Order, Long> {
    Order getOrderById(Integer id);

//    Order OrdersByDate(LocalDate datefrom, LocalDate dateto);

    @Query("SELECT p FROM Order p WHERE p.status=true AND p.date BETWEEN ?1 AND ?2 "
            + "Order by p.id Desc")
    public List<Order> searchOrdersByDates(LocalDate date_from, LocalDate date_to);
}
