package com.fmelectronics.orders.services;

import jakarta.servlet.http.HttpServletResponse;

import java.time.LocalDate;

/**
 * @author Arturo Hdez on 5/17/24;
 * @project fm-electronics-orders-roles
 */
public interface JReportService {
//    void exportJasperReport(HttpServletResponse response) throws JRException, IOException;
    void exportJasperReport3(LocalDate datefrom, LocalDate dateto, HttpServletResponse response);
    void exportJasperReport2(Integer id, HttpServletResponse response);
}
