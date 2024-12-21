package com.fmelectronics.orders.models;

import com.fmelectronics.orders.models.enums.Statusorders;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

//import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "No. Order Required")
    public String noorder;

    @NotEmpty(message = "Date Required")
    @Temporal(TemporalType.DATE)
    public Date date;

    @NotEmpty(message = "Customer Name Required")
    public String customer_name;

    @NotEmpty(message = "Lastname Required")
    public String customer_lastname;

    @NotEmpty(message = "Email Required")
    @Size(max = 50)
    @Email
    public String email;

    @NotEmpty(message = "Phone Required")
    @Size(max = 20)
    public String phone;

    @NotEmpty(message = "Equipment Required")
    public String equipment;

    @NotEmpty(message = "No. Serie Required")
    public String no_serie;
    public String brand;
    @Column(name="malfunction_reported")
    public String malfunction;
    public String accessories;
    // image
    public String image;
    public String url;
    public String notes;
    @Column(name="issues_found")
    public String issues;
    public String solution;

    @Enumerated(EnumType.STRING)
    @Column(name="status_orders", nullable = false, length = 30)
    public Statusorders statusorders;

    public float subtotal;
    public float tax;
    public float total;
    public float labor;
    public String spare1;
    public float cost1;
    public String spare2;
    public float cost2;
    public String spare3;
    public float cost3;
    public String spare4;
    public float cost4;
    public String spare5;
    public float cost5;
    public float spare_parts_cost;
    @Temporal(TemporalType.DATE)
    public Date delivery_date;

    @NotEmpty(message = "Technician Name Required")
    public String tech_name;
    public boolean contact=false;
    public boolean status=true;
    @CreationTimestamp
    @Column(name="date_status",
            columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    public LocalDateTime createDate;

//    public Order(Sort.Direction sortDirection, String s) {
//    }
//    @Temporal(TemporalType.DATE)
//    private Date createdAt;


//    @PrePersist
//    private void onCreate(){
//        createDate = LocalDateTime.now();
//    }
//
//    @PreUpdate
//    private void onUpdate() {
////        createDate = now();
////        if (this.status) {
////            this.createDate = now();
//        this.createDate = LocalDateTime.now();
////        }
//    }

    public Order(long id, String noorder, Date date, String customer_name, String customer_lastname, String email, String phone, String equipment, String no_serie, String brand, String malfunction, String accessories, String image, String url, String notes, String issues, String solution, Statusorders statusorders, float subtotal, float tax, float total, float labor, String spare1, float cost1, String spare2, float cost2, String spare3, float cost3, String spare4, float cost4, String spare5, float cost5, float spare_parts_cost, Date deliveryDate, String techName, boolean contact, boolean status) {
    }

    public Order(long id, String noorder, Date date, String customer_name, String customer_lastname, String email, String phone, String equipment, String no_serie, String brand, String malfunction, String accessories, String images, String url, String notes, Statusorders statusorders, String techName) {
    }

}
