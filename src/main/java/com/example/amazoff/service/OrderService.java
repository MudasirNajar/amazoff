package com.example.amazoff.service;


import com.example.amazoff.model.DeliveryPartner;
import com.example.amazoff.model.Order;
import com.example.amazoff.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    public static Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    OrderRepository orderRepository;

    public String addOrder(Order order) {

        logger.info("We are at the order service");

        return orderRepository.addOrder(order);
    }

    public String addPartner(String id) {
        return orderRepository.addPartner(id);
    }

    public String addPair(String orderId, String partnerId) {
        return orderRepository.addPair(orderId, partnerId);
    }

    public Order findOrder(String id) {
        return orderRepository.findOrder(id);
    }

    public DeliveryPartner findPartner(String partnerId) {
        return orderRepository.findPartner(partnerId);
    }

    public Integer getOrderCountByPartnerId(String id) {
        return orderRepository.orderCountByPartnerId(id);
    }

    public List<String> getOrdersByPartnerId(String partnerId) {
        return orderRepository.ordersByPartnerId(partnerId);
    }

    public List<String> getAllOrders() {
        return orderRepository.findAllOrders();
    }

    public Integer getCountOfUnassignedOrders() {
        return orderRepository.findUnassignedOrders();
    }

    public Integer getOrdersLeftAfterGivenTimeByPartnerId(String partnerId, String time) {
        return orderRepository.getOrdersLeftAfterGivenTimeByPartnerId(partnerId,time);
    }
}
