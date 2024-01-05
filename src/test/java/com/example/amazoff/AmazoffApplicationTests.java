package com.example.amazoff;

import com.example.amazoff.controller.OrderController;
import com.example.amazoff.model.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;


import java.util.Objects;

@SpringBootTest(classes = AmazoffApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AmazoffApplicationTests {

	@Autowired
	OrderController orderController;

	@Test
	public void testAddOrder() {
		Order order = new Order("101","16:20");
		ResponseEntity<String> response = orderController.addOrder(order);
		ResponseEntity<Order> orderResponse = orderController.getOrderById("101");

		if(Objects.nonNull(orderResponse)){
			Assertions.assertEquals("101",orderResponse.getBody().getId());
		}
	}

}
