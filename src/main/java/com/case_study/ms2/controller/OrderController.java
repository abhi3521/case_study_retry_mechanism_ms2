package com.case_study.ms2.controller;

import com.case_study.ms2.dto.OrderEvent;
import com.case_study.ms2.entity.Order;
import com.case_study.ms2.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> addOrder(@RequestBody OrderEvent orderEvent) {
        log.info("Received request to add order with eventId: {}", orderEvent.getEventId());

        try {
            Order order = orderService.addOrder(orderEvent);
            return ResponseEntity.status(HttpStatus.CREATED).body(order);
        } catch (Exception e) {
            log.error("Error adding order: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<Order> getOrderByEventId(@PathVariable String eventId) {
        log.info("Fetching order for eventId: {}", eventId);

        try {
            Order order = orderService.getOrderByEventId(eventId);
            return ResponseEntity.ok(order);
        } catch (Exception e) {
            log.error("Error fetching order: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<Order> getOrderByOrderId(@PathVariable String orderId) {
        log.info("Fetching order for orderId: {}", orderId);

        try {
            Order order = orderService.getOrderByOrderId(orderId);
            return ResponseEntity.ok(order);
        } catch (Exception e) {
            log.error("Error fetching order: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
