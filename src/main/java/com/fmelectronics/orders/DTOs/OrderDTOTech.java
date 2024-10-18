package com.fmelectronics.orders.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fmelectronics.orders.models.enums.Statusorders;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter @Setter
public class OrderDTOTech {

    private long id;
    private String issues;
    private String solution;
    private String image;
    private String url;
    private Statusorders statusorders;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date delivery_date;
}
