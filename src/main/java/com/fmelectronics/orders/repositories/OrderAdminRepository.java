package com.fmelectronics.orders.repositories;

import com.fmelectronics.orders.models.Order;
import com.fmelectronics.orders.models.enums.Statusorders;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Arturo Hdez on 10/18/24;
 * @project fmelectronics-orders
 */
@EnableJpaRepositories
@Repository
public interface OrderAdminRepository extends JpaRepository<Order, Long> {

    List<Order> findByStatusOrderByIdDesc(boolean status2);

    Page<Order> findByStatusOrderByIdDesc(boolean status2, Pageable pageable);
//    Page<Order> findByStatusOrderByIdDesc(String no_order, boolean status2, Pageable pageable);
    Page<Order> findByNotesContainingAndStatusOrderByIdDesc(String no_order, boolean status2, Pageable pageable);
    Page<Order> findByNotesContainingOrBrandContainingAndStatusOrderByIdDesc(String no_order, String brand, boolean status2, Pageable pageable);

    @Query("SELECT p FROM Order p WHERE p.status=true AND p.id=?1")
    public Order OrderById(Integer id);

    List<Order> findByStatusAndStatusOrdersOrderByIdDesc(boolean status, Statusorders statusorders);
    @Query("SELECT p FROM Order p WHERE p.status=?1 AND p.statusorders=?2 Order by p.id Desc")
    public List<Order> searchOrdersByStatusOrders(boolean status, Statusorders Statusorders);

    @Query("SELECT p FROM Order p WHERE p.status=true " +
            "AND p.statusOrders='New_Order' Order by p.id Desc")
    public List<Order> listNewOrder();

            @Query("SELECT p FROM Order p WHERE p.status=true "
            + " AND p.no_order LIKE %?1%"
            + " OR p.customer_name LIKE %?1%"
            + " OR p.customer_lastname LIKE %?1%"
            + " OR p.statusOrders LIKE %?1%"
            + " OR p.brand LIKE %?1%"
            + " OR p.equipment LIKE %?1%"
            + " OR p.tech_name LIKE %?1%"
            + " OR CONCAT(p.date, '') LIKE %?1%"
            + "Order by p.id Desc")
    public List<Order> searchOrders(String keyword);

    List<Order> findByDateBetweenOrderByDateDesc(LocalDate date1, LocalDate date2);
    @Query("SELECT p FROM Order p WHERE p.status=true AND p.date BETWEEN ?1 AND ?2 "
            + "Order by p.id Desc")
    public List<Order> searchOrdersByDates(LocalDate date_from, LocalDate date_to);

    @Query("UPDATE Order p set p.status=false Where p.id = ?1")
    @Transactional
    @Modifying
    void updatedStatus(long id);

    @Query("SELECT COUNT(s) FROM Order s WHERE s.customer_name = :name AND s.status=:b")
    long countByName(@Param("name") String name, boolean b);

    @Query("SELECT COUNT(s) FROM Order s WHERE s.status = :b AND s.statusorders = :statusorders")
    long countByStatusOrders(boolean b, Statusorders statusorders);

    @Query("SELECT COUNT(s) FROM Order s WHERE s.status = :b AND s.statusorders = :statusorders")
    long countByStatusOrders2(boolean b, Statusorders statusorders);

    @Query("SELECT MAX(s.no_order) FROM Order s")
    long noSerie();

//    Page<Order> findByStatusOrderByIdDesc(boolean status2, Pageable pagingSort);
//
//    Page<Order> findByStatusOrderByIdDesc(boolean status2, String noOrder, Pageable pagingSort);
//    limit 1

//    REPAIRED,
//    PENDING,
//    WAITING_FOR_SPARE_PARTS,
//    WAITING_FOR_APPROVAL,
//    REPARATION_DISAPPROVED,
//    DELIVERED

}
