package com.fmelectronics.orders.controllers;

import com.fmelectronics.orders.DTOs.OrderDTO;
import com.fmelectronics.orders.DTOs.OrderDTOAll;
import com.fmelectronics.orders.DTOs.OrderDTOImage;
import com.fmelectronics.orders.DTOs.UpdateStatusOrders;
import com.fmelectronics.orders.models.Order;
import com.fmelectronics.orders.models.Role;
import com.fmelectronics.orders.models.enums.Statusorders;
import com.fmelectronics.orders.exceptions.NotFoundException;
import com.fmelectronics.orders.payload.response.ResponseConstants;
import com.fmelectronics.orders.repositories.OrderAdminRepository;
import com.fmelectronics.orders.repositories.RoleRepository;
import com.fmelectronics.orders.services.JReportService;
import com.fmelectronics.orders.services.OrderAdminService;
import com.fmelectronics.orders.util.StandardResponse;
//import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

/**
 * @author Arturo Hdez on 10/18/24;
 * @project fmelectronics-orders
 */

@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "Orders Admin", exposedHeaders = "X-Get-Header")
@RestController
@RequestMapping("/api/order")
//@Tag(name = "Admin", description = "Admin management APIs")
public class OrderAdminController {

    @Autowired
    OrderAdminRepository orderAdminRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    OrderAdminService orderAdminService;
//
//    public Sort.Direction getSortDirection(String direction) {
//        if (direction.equals("asc")) {
//            return Sort.Direction.ASC;
//        } else if (direction.equals("desc")) {
//            return Sort.Direction.DESC;
//        }
//        return Sort.Direction.ASC;
//    }
//
//    @GetMapping("/status4")
//    public ResponseEntity<Map<String, Object>> getAllTutorialsPage(
//            @RequestParam(required = false) String no_order,
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "3") int size,
//            @RequestParam(defaultValue = "id,desc") String[] sort) {
//
//        try {
//            List<Order> orders = new ArrayList<Order>();
//
//            if (sort[0].contains(",")) {
//                // will sort more than 2 fields
//                // sortOrder="field, direction"
//                for (String sortOrder : sort) {
//                    String[] _sort = sortOrder.split(",");
//                    orders.add(new Order(getSortDirection(_sort[1]), _sort[0]));
//                }
//            } else {
//                // sort=[field, direction]
//                orders.add(new Order(getSortDirection(sort[1]), sort[0]));
//            }
//
//            List<Order> tutorials = new ArrayList<Order>();
//            Pageable pagingSort = PageRequest.of(page, size, Sort.by((Sort.Order) orders));
//
//            Page<Order> pageTuts;
//            if (no_order == null)
//                pageTuts = orderAdminRepository.findByStatusOrderByIdDesc(true, pagingSort);
//            else
////                pageTuts = orderAdminRepository.findByTitleContaining(title, pagingSort);
//                pageTuts = orderAdminRepository.findByStatusOrderByIdDesc(true, no_order, pagingSort);
//
//            tutorials = pageTuts.getContent();
//
//            Map<String, Object> response = new HashMap<>();
//            response.put("orders", tutorials);
//            response.put("currentPage", pageTuts.getNumber());
//            response.put("totalItems", pageTuts.getTotalElements());
//            response.put("totalPages", pageTuts.getTotalPages());
//
//            return new ResponseEntity<>(response, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @PostMapping("/image")
    public ResponseEntity<OrderDTOImage> save(@RequestPart MultipartFile file, @RequestBody OrderDTOImage dtoImage) throws IOException {
//        return new ResponseEntity<>(orderAdminService.save(dtoImage, file), HttpStatus.CREATED);
        orderAdminService.save(dtoImage, file);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/image")
    public ResponseEntity<List<OrderDTOImage>> getImages(){
        return new ResponseEntity<>(orderAdminService.getImages(), HttpStatus.OK);
    }

    @GetMapping("/image/{id}")
    public ResponseEntity<OrderDTOImage>  get(@PathVariable Long id){
        return new ResponseEntity<>(orderAdminService.get(id), HttpStatus.OK);
    }

    //image
    @PostMapping("/")
    public ResponseEntity<OrderDTOImage> saveOrders(@Valid @RequestBody MultipartFile file, OrderDTOImage ordersDTO) throws IOException {
        try {
            OrderDTOImage newOrder = orderAdminService.save(ordersDTO, file);
            return new ResponseEntity<>(newOrder, HttpStatus.CREATED);

//            return new ResponseEntity<>(orderAdminService.save(ordersDTO, file), HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/status3")
    public ResponseEntity<Map<String, Object>> findByPublished(
            @RequestParam(required = false) String no_order,
            @RequestParam(required = false) String brand,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size) {

        try {
            List<Order> orders = new ArrayList<Order>();
            Pageable paging = PageRequest.of(page, size);

            Page<Order> pageTuts;


            log.info("Variable notes " + no_order);
            log.info("Variable brand " + brand);

            if (no_order == null && brand == null)
                pageTuts = orderAdminRepository.findByStatusOrderByIdDesc(true, paging);
            else
//                pageTuts = orderAdminRepository.findByStatusOrderByIdDesc(true, paging);
//                pageTuts = orderAdminRepository.findByNotesContainingAndStatusOrderByIdDesc(no_order, true, paging);
                pageTuts = orderAdminRepository.findByNotesContainingOrBrandContainingAndStatusOrderByIdDesc(no_order, brand, true, paging);

//            Page<Order> pageTuts = orderAdminRepository.findByStatusOrderByIdDesc(true, no_order, paging);
//            Page<Order> pageTuts = orderAdminRepository.findByStatusOrderByIdDesc(true, paging);
            orders = pageTuts.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("orders", orders);
            response.put("currentPage", pageTuts.getNumber());
            response.put("totalItems", pageTuts.getTotalElements());
            response.put("totalPages", pageTuts.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/status")
//    @PreAuthorize("hasRole('ADMIN')")
//    @PreAuthorize("hasRole('ADMIN') or hasRole('SELLER') or hasRole('TECHNICIAN')")
    public ResponseEntity<List<Order>> listProductsTrueStatus(){
        List<Order> catalogs2 = orderAdminService.findByStatus(true);
        if(catalogs2.isEmpty()){
            return new ResponseEntity<>(catalogs2, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(catalogs2, HttpStatus.OK);

//        List<Order> orders = orderAdminService.findByStatus(true);
//        try {
////            orderAdminService.findByStatus(true);
////            return new ResponseEntity<>(new StandardResponse("200", "Done", orderAdminService.findByStatus(true)), HttpStatus.OK);
//            return new ResponseEntity<>(orders, HttpStatus.OK);
//        } catch (Exception e){
////            return ResponseEntity.badRequest().body(new MessageResponse("INTERNAL SERVER ERROR!"));
//            return new ResponseEntity<>(new StandardResponse("500", "Error", orderAdminService.findByStatus(true)), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
    }

    @GetMapping("/status/order/{id}")
    //    @PreAuthorize("hasRole('ADMIN') or hasRole('SELLER') or hasRole('TECHNICIAN')")
    public ResponseEntity<?> listOrderById(@PathVariable Integer id){
        try{
            Order orderById = orderAdminService.getOrderById(id);
            return new ResponseEntity<>(orderById, HttpStatus.OK);
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return StandardResponse.getResponseEntity(ResponseConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> listRoles(){
        List<Role> roles = roleRepository.findAll();
        if(roles.isEmpty()){
            return new ResponseEntity<>(roles, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(roles, HttpStatus.OK);
//        return new ResponseEntity(new StandardResponse("200", "Done", roles), HttpStatus.OK);
    }

//    Order front
    @GetMapping("/newOrder")
    public ResponseEntity<List<Order>> listNewRoles(){
        List<Order> newOrder = orderAdminRepository.listNewOrder();
        if(newOrder.isEmpty()){
            return new ResponseEntity<>(newOrder, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(newOrder, HttpStatus.OK);
    }

    @GetMapping("/status/{statusOrders}")
//    @PreAuthorize("hasRole('ADMIN') or hasRole('SELLER') or hasRole('TECHNICIAN')")
    public ResponseEntity<List<Order>> listOrderTrueStatus2(@PathVariable("statusOrders") Statusorders statusorders)throws NotFoundException {
        try {
            return orderAdminService.findByStatusOrders(true, statusorders);
//            orderAdminService.findByStatusOrders(true, statusOrders);
//            return new ResponseEntity<>(roles, HttpStatus.OK);
//            return new ResponseEntity(new StandardResponse("200", "Done", orderAdminService.findByStatusOrders(true, statusOrders)), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
//            orderAdminService.findByStatusOrders(true, statusOrders);
//            return new ResponseEntity<>(roles, HttpStatus.NO_CONTENT);
//            return new ResponseEntity(new StandardResponse("500", "Error", orderAdminService.findByStatusOrders(true, statusOrders)), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return null;
    }

    @GetMapping("/search")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SALESPERSON') or hasRole('TECHNICIAN')")
    public ResponseEntity<List<Order>> searchOrders(@RequestParam(name = "keyword", defaultValue = "") String keyword1){
        try {
            return new ResponseEntity<>(orderAdminRepository.searchOrders(keyword1),null, HttpStatus.OK);

//            List<Order> dateOrders = orderAdminRepository.searchOrders(keyword1);
//            return new ResponseEntity<>(orderAdminRepository.searchOrders(keyword1),null, HttpStatus.OK);
//            return new ResponseEntity<>(new StandardResponse("200", "Done", orderAdminRepository.searchOrders(keyword1)), HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
//            return new ResponseEntity<>(new StandardResponse("500", "Error", orderAdminRepository.searchOrders(keyword1)), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return null;
    }

    @GetMapping("/dates")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> searchOrdersByDates(@RequestParam(name = "dateS", defaultValue = "") LocalDate dateS, @RequestParam(name = "dateE", defaultValue = "")LocalDate dateE ){
        try {
            return new ResponseEntity<>(orderAdminRepository.searchOrdersByDates(dateS, dateE),null, HttpStatus.OK);
//            return new ResponseEntity<>(new StandardResponse("200", "Done", orderAdminRepository.searchOrdersByDates(dateS, dateE)), HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
//            return new ResponseEntity<>(new StandardResponse("500", "Error", orderAdminRepository.searchOrdersByDates(dateS, dateE)), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return null;
    }

//    @GetMapping("/sdate/{date1}/edate/{date2}")
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<?> searchByDates(@PathVariable LocalDate date1, @PathVariable LocalDate date2){
//        try {
//            ResponseEntity<?> optional = orderAdminService.findByDateBetweenOrderByDateDesc(date1, date2);
////            return new ResponseEntity<List<Order>>((List<Order>) orderAdminService.findByDateBetweenOrderByDateDesc(date1, date2), HttpStatus.OK);
//            return new ResponseEntity<>(new StandardResponse("200", "Done", optional), HttpStatus.OK);
//        }catch (Exception e){
////            e.printStackTrace();
//            return new ResponseEntity<>(new StandardResponse("500", "Error", "Orders per Dates: Internal Error"), HttpStatus.NOT_FOUND);
//        }
////        return new ResponseEntity<>(new StandardResponse("500", "Error", "Orders per Dates: Internal Error"), HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    // create orders for everyone
    @PostMapping("/")
    public ResponseEntity<OrderDTO> saveOrders(@Valid @RequestBody OrderDTO ordersDTO){
//        @Valid
        try {
            OrderDTO newOrder = orderAdminService.save(ordersDTO);
            return new ResponseEntity<OrderDTO>(newOrder, HttpStatus.CREATED);
//            ResponseEntity<?> newOrder = orderAdminService.save(ordersDTO);
//            return new ResponseEntity<>(new StandardResponse("200", "Done", orderAdminService.save(ordersDTO)), HttpStatus.CREATED);
        }catch (Exception e){
           e.printStackTrace();
//            return new ResponseEntity<>(new StandardResponse("500", "Error", orderAdminService.save(ordersDTO)), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return null;
//        return StandardResponse.getResponseEntity(ResponseConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
//
//    @PostMapping("/send")
//    public ResponseEntity<String> sendCatalog(@RequestBody @Valid SendCatalogsDTO sendCatalogsDTO) throws MessagingException {
//        SendCatalogsDTO sendCatalog = catalogsService.sendCatalogs(sendCatalogsDTO);
//        return new ResponseEntity<>("Sent Links Successfully", HttpStatus.CREATED);
//    }
//
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Order> updateOrder(@PathVariable("id") long orderId, @Valid @RequestBody Order orders)throws Exception{
        try {
            orders.setId(orderId);
            Order updatedOrders = orderAdminService.update(orders);
            return new ResponseEntity<>(orders, HttpStatus.OK);
//            return new ResponseEntity<>(new StandardResponse("200", "Done", orderAdminService.update(ordersDTO)), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
//            return new ResponseEntity<>(new StandardResponse("500", "Error", orderAdminService.update(ordersDTO)), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updatedStatus(@PathVariable("id") long orderId){
        try {
            ResponseEntity<?> optional = orderAdminService.updatedStatus(orderId);
            return new ResponseEntity<>("Order Disabled!", HttpStatus.OK);
//            return new ResponseEntity<>(new StandardResponse("200", "Done", optional), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
//            return new ResponseEntity<>(new StandardResponse("500", "Error", "INTERNAL"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return StandardResponse.getResponseEntity(ResponseConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PatchMapping("/orderstatus/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateOrderStatus(@PathVariable("id") long orderId, @Valid @RequestBody UpdateStatusOrders statusOrderDTO)throws Exception{
        try {
            statusOrderDTO.setId(orderId);
            orderAdminService.updateOrderStatus(statusOrderDTO);
            return new ResponseEntity<>("Order Status Updated!", HttpStatus.OK);

//            return new ResponseEntity<>(new StandardResponse("200", "Done", orderAdminService.updateOrderStatus(statusOrderDTO)), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
//            return new ResponseEntity<>(new StandardResponse("500", "Error", orderAdminService.updateOrderStatus(statusOrderDTO)), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return StandardResponse.getResponseEntity(ResponseConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Autowired
    JReportService jReportService;

//    @GetMapping("/pdf/export")
//    @PreAuthorize("hasRole('ADMIN') or hasRole('SELLER') or hasRole('TECHNICIAN')")
//    public void createPDF(HttpServletResponse response) throws IOException, JRException {
//        response.setContentType("application/pdf");
//        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
//        String currentDateTime = dateFormatter.format(new Date());
//        String headerKey = "Content-Disposition";
//        String headerValue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
//        response.setHeader(headerKey, headerValue);
//        jReportService.exportJasperReport(response);
//    }

    @GetMapping("/pdf/export/dates")
//    @PreAuthorize("hasRole('ADMIN')")
    public void createPDF3(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date_from,
                           @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate date_to ,
                           HttpServletResponse response) throws IOException, JRException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
        jReportService.exportJasperReport3(date_from, date_to, response);
    }

    @GetMapping("/pdf/export/{id}")
//    @PreAuthorize("hasRole('ADMIN') or hasRole('SELLER')")
    public void createPDF2(@PathVariable("id")Integer id, HttpServletResponse response) throws IOException, JRException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
        jReportService.exportJasperReport2(id, response);
    }

    @GetMapping("/no_serie")
    public ResponseEntity<?> listOrder(){
        try {
            long roles = orderAdminRepository.noSerie();
            return new ResponseEntity<>(roles, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/no_serie2")
//    @PreAuthorize("hasRole('ADMIN') or hasRole('SELLER') or hasRole('TECHNICIAN')")
    public ResponseEntity<List<Order>> noSerie(@RequestParam(required = false) String noSerie) {
        try {
            List<Order> tutorials = new ArrayList<Order>();
            if (noSerie.isEmpty()) {
                orderAdminRepository.noSerie();
                return new ResponseEntity<>(tutorials, HttpStatus.OK);
            }else{
                orderAdminRepository.noSerie();
//            if (tutorials.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(tutorials, HttpStatus.OK);
        }  catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
