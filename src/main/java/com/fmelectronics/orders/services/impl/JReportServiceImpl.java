package com.fmelectronics.orders.services.impl;

import com.fmelectronics.orders.models.Order;
import com.fmelectronics.orders.repositories.JReportRepository;
import com.fmelectronics.orders.services.JReportService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Arturo Hdez on 5/17/24;
 * @project fm-electronics-orders-roles
 */
@Service
@Slf4j
public class JReportServiceImpl implements JReportService {

    @Autowired
    JReportRepository jReportRepository;

//    public void exportJasperReport(HttpServletResponse response) throws JRException, IOException {
//        List<Order> address = jReportRepository.findAll();
//        //Get file and compile it
//        File file = ResourceUtils.getFile("classpath:pdf-templates/employees.jrxml");
//        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
//        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(address);
//        Map<String, Object> parameters = new HashMap<>();
//        parameters.put("createdBy", "Simplifying Tech");
//        //Fill Jasper report
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
//        //Export report
//        JasperExportManager.exportReportToPdfStream(jasperPrint,response.getOutputStream());
//    }

    public void exportJasperReport3(LocalDate date_from, LocalDate date_to, HttpServletResponse response) {
        try {
            List<Order> order = jReportRepository.searchOrdersByDates(date_from, date_to);
//                    .orElseThrow(() -> new NotFoundException("Order Not found with id = " + id));
//            log.info("Id= "+ id);
            log.info("Order= "+ order);
            log.info("Date from: "+ date_from + " Date to: " + date_to);

            //Get file and compile it
            File file = ResourceUtils.getFile("classpath:pdf-templates/fmordersdates.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(order);
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("datefrom", date_from);
            parameters.put("dateto", date_to);
//        parameters.put("createdBy", "Simplifying Tech");

            //Fill Jasper report
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
//            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters);

            //Export report
            JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exportJasperReport2(Integer id, HttpServletResponse response) {
        try {
            Order order = jReportRepository.getOrderById(id);
//                    .orElseThrow(() -> new NotFoundException("Order Not found with id = " + id));
            log.info("Id= "+ id);
            log.info("Order= "+ order.getNoorder());

            //Get file and compile it
            File file = ResourceUtils.getFile("classpath:pdf-templates/invoice.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(Collections.singleton(order));
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("id", id);
//        parameters.put("createdBy", "Simplifying Tech");

            //Fill Jasper report
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
//            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters);

            //Export report
            JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
