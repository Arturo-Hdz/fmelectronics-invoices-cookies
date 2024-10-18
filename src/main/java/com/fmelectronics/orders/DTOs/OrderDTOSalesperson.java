package com.fmelectronics.orders.DTOs;

import com.fmelectronics.orders.models.enums.Statusorders;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter @Setter
public class OrderDTOSalesperson {

    private long id;

    private String notes;
    private Statusorders statusorders;
    private boolean contact;

}
