package com.fmelectronics.orders.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fmelectronics.orders.models.enums.Statusorders;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter @Setter
public class OrderDTOAll {

    private long id;

    @NotNull(message = "No Order Dto cannot be null")
    @Size(min = 1, message = "No Order Dto should have at least 1 character")
    @NotEmpty(message = "No Order required")
    private String noorder;

//    @NotEmpty(message = "Date required")
    @NotNull(message = "Date Dto cannot be null")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate date;

    @NotNull(message = "Customer Name Dto cannot be null")
    @Size(min = 1, message = "Customer Name Dto should have at least 1 character")
    @NotEmpty(message = "Customer Name required")
    private String customer_name;

    @NotNull(message = "Customer Lastname Dto cannot be null")
    @Size(min = 1, message = "Customer Lastname Dto should have at least 1 character")
    @NotEmpty(message = "Customer Lastname required")
    private String customer_lastname;

    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")
    private String email;

    @Pattern(regexp = "^((\\(\\d{3}\\))|\\d{3})[- ]?\\d{3}[- ]?\\d{4}$")
    @NotEmpty(message = "Phone required")
    private String phone;

    @NotNull(message = "Equipment Dto cannot be null")
    @Size(min = 1, message = "Equipment Dto should have at least 1 character")
    @NotEmpty(message = "Equipment required")
    private String equipment;

    @NotNull(message = "No Serie Dto cannot be null")
    @Size(min = 1, message = "No Serie Dto should have at least 1 character")
    @NotEmpty(message = "No Serie required")
    private String no_serie;

    @NotNull(message = "Brand Dto cannot be null")
    @Size(min = 1, message = "Brand Dto should have at least 1 character")
    @NotEmpty(message = "Brand required")
    private String brand;

    @NotNull(message = "Malfunction Dto cannot be null")
    @Size(min = 1, message = "Malfunction Dto should have at least 1 character")
    @NotEmpty(message = "Malfunction required")
    private String malfunction;

//    @NotEmpty(message = "Accesories required")
    private String accessories;
    private String image;
    private String url;
    private String notes;

    @NotNull(message = "Issues Dto cannot be null")
    @Size(min = 1, message = "Issues Dto should have at least 1 character")
    @NotEmpty(message = "Issues Found required")
    private String issues;

    @NotNull(message = "Solution Dto cannot be null")
    @Size(min = 1, message = "Solution Dto should have at least 1 character")
    @NotEmpty(message = "Solution required")
    private String solution;

    @NotNull(message = "Status Order Dto cannot be null")
//    @NotEmpty(message = "Status Order required")
//    @Size(min = 1, message = "Status Order Dto should have at least 1 character")
//    @ValidateString(values={"String","Boolean", "Integer"})
//    @ValueOfEnum(enumClass = StatusOrders.class)
//    @EnumNamePattern(regexp = "NEW|DEFAULT")
    private Statusorders statusorders;

//    @Column(name="taxes")
//    @NotEmpty(message = "Taxes required")
    private float tax;

    @DecimalMin(value = "0.1", inclusive = false)
    @DecimalMax(value = "10000", inclusive = false)
    private float subtotal;

    @DecimalMin(value = "0.1", inclusive = false)
    @DecimalMax(value = "10000", inclusive = false)
    private float total;

    @DecimalMin(value = "0.1", inclusive = false)
    @DecimalMax(value = "10000", inclusive = false)
    private float labor;

    private String spare1;
    private float cost1;
    private String spare2;
    private float cost2;
    private String spare3;
    private float cost3;
    private String spare4;
    private float cost4;
    private String spare5;
    private float cost5;

    @DecimalMin(value = "0.1", inclusive = false)
    @DecimalMax(value = "10000", inclusive = false)
    private float spare_parts_cost;

    //    @NotEmpty(message = "Delivery Date required")
    @NotNull(message = "Date Dto cannot be null")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate delivery_date;

    @NotNull(message = "Tech name Dto cannot be null")
    @Size(min = 1, message = "Tech name Dto should have at least 1 character")
    @NotEmpty(message = "Tech name Found required")
    private String tech_name;

    @Column(name = "contact")
    private boolean contact;

    @Column(name = "status")
    private boolean status=true;

//    @CreationTimestamp
//    @Column(name="dateStatus",
//            columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
//    @Column(name = "createdate")

//    @Temporal(TemporalType.TIMESTAMP)
//    @UpdateTimestamp
////    @NonNull
//    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
//    private LocalDateTime createDate;
//
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
}
