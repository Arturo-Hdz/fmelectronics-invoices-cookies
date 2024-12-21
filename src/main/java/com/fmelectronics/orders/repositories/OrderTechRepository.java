package com.fmelectronics.orders.repositories;

import com.fmelectronics.orders.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

/**
 * @author Arturo Hdez on 3/26/24;
 * @project back-orders
 */
@EnableJpaRepositories
public interface OrderTechRepository extends JpaRepository<Order, Long> {

    List<Order> findByStatusOrderByIdDesc(boolean status2);

        List<Order> findByStatusAndStatusordersOrderByIdDesc(boolean status, String Statusorders);

        @Query("SELECT p FROM Order p WHERE p.status=true " +
            "AND p.statusorders='NEW_ORDER' OR p.statusorders='REPAIRED' " +
            "OR p.statusorders='PENDING' OR p.statusorders='WAITING_FOR_SPARE_PARTS'" +
            " OR p.statusorders='WAITING_FOR_APPROVAL' OR p.statusorders='REPARATION_DISAPPROVED'" +
            "Order by p.id Desc")
    public List<Order> listOrderTech();

    @Query("SELECT p FROM Order p WHERE p.status=?1"
            + " AND p.noorder LIKE %?2%"
            + " OR p.customer_name LIKE %?2%"
            + " OR p.customer_lastname LIKE %?2%"
            + " OR p.statusorders LIKE %?2%"
            + " OR CONCAT(p.date, '') LIKE %?2%" +
            "Order by p.id Desc")
    public List<Order> searchOrders(boolean status2, String keyword);

    //    @Query("SELECT c FROM Catalogs c WHERE c.status=?1 AND CONCAT(c.type, c.title,c.subtitle, c.year) LIKE %?2% " +
//            "Order By c.id Desc")
//    public List<Catalogs> searchCatalogs(boolean status, String keyword);

//    @Query("UPDATE Order p set p.status=false Where p.id = ?1")
//    @Transactional
//    @Modifying
//    public void updateStatus(long id);

}
