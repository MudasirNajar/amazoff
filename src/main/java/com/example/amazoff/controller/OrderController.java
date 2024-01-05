package com.example.amazoff.controller;

import com.example.amazoff.model.DeliveryPartner;
import com.example.amazoff.model.Order;
import com.example.amazoff.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    public static Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    OrderService orderService;

    @PostMapping("/add-order")
    public ResponseEntity<String> addOrder(@RequestBody Order order) {

        logger.trace("This is trace Tushar");
        logger.info("This is info Tushar");
        logger.debug("This is debug Tushar");
        logger.warn("This is warning Tushar");
        logger.error("This is error Tushar");

        logger.info("We are at order controller");
        logger.trace("we are at the order controller and the order object is " + order);
        String ans = null;
        try {
            ans = orderService.addOrder(order);
            return new ResponseEntity<>(ans, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("we have got an error in add-order " + e.getMessage());
        }
        return null;
    }

    @PostMapping("/add-partner/{partnerId}")
    public ResponseEntity<String> addPartner(@PathVariable("partnerId") String id) {
        String ans = orderService.addPartner(id);
        return new ResponseEntity<>(ans, HttpStatus.CREATED);
    }

    @PutMapping("/add-order-partner-pair")
    public ResponseEntity<String> addOrderPartnerPair(@RequestParam("orderId") String orderId,
                                                      @RequestParam("partnerId") String partnerId) {
        String ans = orderService.addPair(orderId, partnerId);
        return new ResponseEntity<>(ans, HttpStatus.CREATED);
    }

    @GetMapping("/get-order-by-id/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable("orderId") String id) {
        Order order = orderService.findOrder(id);
        return new ResponseEntity<>(order, HttpStatus.FOUND);
    }

    @GetMapping("/get-partner-by-id/{partnerId}")
    public ResponseEntity<DeliveryPartner> getPartnerById(@PathVariable String partnerId) {
        DeliveryPartner partner = orderService.findPartner(partnerId);
        return new ResponseEntity<>(partner, HttpStatus.FOUND);
    }

    @GetMapping("/get-order-count-by-partner-id/{partnerId}")
    public ResponseEntity<Integer> getOrderCountByPartnerId(@PathVariable String partnerId) {
        int totalOrders = orderService.getOrderCountByPartnerId(partnerId);
        return new ResponseEntity<>(totalOrders, HttpStatus.FOUND);
    }

    @GetMapping("/get-orders-by-partner-id/{partnerId}")
    public ResponseEntity<List<String>> getOrdersByPartnerId(@PathVariable("partnerId") String partnerId) {
        List<String> orders = orderService.getOrdersByPartnerId(partnerId);
        return new ResponseEntity<>(orders, HttpStatus.FOUND);
    }

    @GetMapping("/get-all-orders")
    public ResponseEntity<List<String>> getAllOrders() {
        List<String> allOrders = orderService.getAllOrders();
        return new ResponseEntity<>(allOrders, HttpStatus.FOUND);
    }

    @GetMapping("/get-count-of-unassigned-orders")
    public ResponseEntity<Integer> getCountOfUnassignedOrders() {
        int unassignedOrders = orderService.getCountOfUnassignedOrders();
        return new ResponseEntity<>(unassignedOrders, HttpStatus.FOUND);
    }

    @GetMapping("/get-count-of-orders-left-after-given-time/{partnerId}/{time}")
    public ResponseEntity<Integer> getOrdersLeftAfterGivenTimeByPartnerId(@PathVariable String partnerId,
                                                                          @PathVariable String time) {
       int orderCount = orderService.getOrdersLeftAfterGivenTimeByPartnerId(partnerId,time);
        return new ResponseEntity<>(orderCount, HttpStatus.FOUND);
    }
}
