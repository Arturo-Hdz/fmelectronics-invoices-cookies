package com.fmelectronics.orders.DTOs;

import com.fmelectronics.orders.models.enums.Statusorders;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateStatusOrders {
  private long id;
  private Statusorders statusorders;
//  private String refreshToken;

}
