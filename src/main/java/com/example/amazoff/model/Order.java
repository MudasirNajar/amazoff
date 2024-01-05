package com.example.amazoff.model;

public class Order {
    private String id;
    private String deliveryTime;

    public Order() {
    }

    public Order(String id, String deliveryTime) {

        // The deliveryTime has to be converted from string to int and then stored in the attribute
        //deliveryTime  = HH*60 + MM
        this.id = id;
//        int hours = Integer.parseInt(deliveryTime.substring(0, 2));
//        int minutes = Integer.parseInt(deliveryTime.substring(3));

//                = hours * 60 + minutes;
        this.deliveryTime = deliveryTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }
}
