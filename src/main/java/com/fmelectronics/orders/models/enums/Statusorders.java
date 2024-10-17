package com.fmelectronics.orders.models.enums;

public enum Statusorders {
  NEW_ORDER("NEW_ORDER"),
  REPAIRED("REPAIRED"),
  PENDING("PENDING"),
  WAITING_FOR_SPARE_PARTS("WAITING_FOR_SPARE_PARTS"),
  WAITING_FOR_APPROVAL("WAITING_FOR_APPROVAL"),
  REPARATION_DISAPPROVED("REPARATION_DISAPPROVED"),
  DELIVERED("DELIVERED");

  public String SOString;

  public String getSOString() {
    return this.SOString;
  }
  private Statusorders(String SOString){
    this.SOString = SOString;
  }
}
