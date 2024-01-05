package com.example.amazoff.repository;

import com.example.amazoff.model.DeliveryPartner;
import com.example.amazoff.model.Order;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Repository
public class OrderRepository {

    HashMap<String, Order> orderMap = new HashMap<>();
    HashMap<String, DeliveryPartner> partnerMap = new HashMap<>();
    HashMap<String, String> orderToPartnerMap = new HashMap<>();            // key : orderId , value : partnerId
    HashMap<String, HashSet<String>> partnerToOrderMap = new HashMap<>();   // key : partnerId , value : HashSet<orderId>


    public String addOrder(Order order) {
        String key = order.getId();
        orderMap.put(key, order);
        return "Order placed successfully";
    }

    public String addPartner(String partnerId) {
        DeliveryPartner deliveryPartner = new DeliveryPartner(partnerId);
        partnerMap.put(partnerId, deliveryPartner);
        return "Partner added successfully";
    }

    public String addPair(String orderId, String partnerId) {

        if (orderMap.containsKey(orderId) && partnerMap.containsKey(partnerId)) {
            HashSet<String> currentOrders = new HashSet<>();
            if (partnerToOrderMap.containsKey(partnerId)) {
                currentOrders = partnerToOrderMap.get(partnerId);
            }

            currentOrders.add(orderId);
            partnerToOrderMap.put(partnerId, currentOrders);
            DeliveryPartner partner = partnerMap.get(partnerId);
            partner.setNumberOfOrders(currentOrders.size());

            return "Order assigned to the partner";
        }
        return null;
    }

    public Order findOrder(String id) {
        if (orderMap.containsKey(id)) {
            return orderMap.get(id);
        }
        return null;
    }

    public DeliveryPartner findPartner(String partnerId) {
        return partnerMap.get(partnerId);
    }

    public Integer orderCountByPartnerId(String partnerId) {

        int orderCount = 0;
        if (partnerToOrderMap.containsKey(partnerId)) {
            orderCount = partnerToOrderMap.get(partnerId).size();
        }
        return orderCount;
    }

    public List<String> ordersByPartnerId(String partnerId) {
        HashSet<String> orderList = null;
        if (partnerToOrderMap.containsKey(partnerId))
            orderList = partnerToOrderMap.get(partnerId);

        assert orderList != null;
        return new ArrayList<>(orderList);
    }

    public List<String> findAllOrders() {
        return new ArrayList<>(orderMap.keySet());
    }

    public Integer findUnassignedOrders() {
        int unassignedOrderCount = 0;
        int assignedOrderCount = 0;

        for (DeliveryPartner partner : partnerMap.values()) {
            assignedOrderCount += partner.getNumberOfOrders();
        }

        unassignedOrderCount = orderMap.size() - assignedOrderCount;
        return unassignedOrderCount;
    }

    public Integer getOrdersLeftAfterGivenTimeByPartnerId(String partnerId, String time) {

        int hours = Integer.parseInt(time.substring(0, 2));
        int minutes = Integer.parseInt(time.substring(3));
        int timeInMinutes = hours * 60 + minutes;
        int undeliveredOrderCount = 0;

        if (partnerToOrderMap.containsKey(partnerId)) {
            HashSet<String> assignedOrders = partnerToOrderMap.get(partnerId);
            for (String orderId : assignedOrders) {
                Order order = orderMap.get(orderId);
                String orderDeliveryTime = order.getDeliveryTime();
                hours = Integer.parseInt(orderDeliveryTime.substring(0, 2));
                minutes = Integer.parseInt(orderDeliveryTime.substring(3));
                int orderDeliveryTimeInMinutes = hours * 60 + minutes;
                if (orderDeliveryTimeInMinutes >= timeInMinutes) {
                    undeliveredOrderCount++;
                }
            }
            return undeliveredOrderCount;
        }
        return undeliveredOrderCount;
    }
}
