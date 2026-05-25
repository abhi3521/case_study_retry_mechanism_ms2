package com.case_study.ms2.service;

import com.case_study.ms2.dto.OrderEvent;
import com.case_study.ms2.entity.Order;
import com.case_study.ms2.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public Order addOrder(OrderEvent orderEvent) {
        log.info("Adding order with eventId: {}", orderEvent.getEventId());

        try {
            // Check if order already exists to avoid duplicates
            if (orderRepository.findByEventId(orderEvent.getEventId()).isPresent()) {
                log.warn("Order already exists for eventId: {}", orderEvent.getEventId());
                return orderRepository.findByEventId(orderEvent.getEventId()).get();
            }

            Order order = Order.builder()
                    .eventId(orderEvent.getEventId())
                    .orderId(orderEvent.getOrderId())
                    .customerId(orderEvent.getCustomerId())
                    .amount(orderEvent.getAmount())
                    .status(orderEvent.getStatus())
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();

            Order savedOrder = orderRepository.save(order);
            log.info("Order saved successfully with id: {} and eventId: {}",
                    savedOrder.getId(),
                    savedOrder.getEventId());

            return savedOrder;

        } catch (Exception e) {
            log.error("Failed to add order with eventId: {}. Error: {}",
                    orderEvent.getEventId(),
                    e.getMessage());
            throw new RuntimeException("Failed to add order", e);
        }
    }

    public Order getOrderByEventId(String eventId) {
        log.info("Fetching order for eventId: {}", eventId);
        return orderRepository.findByEventId(eventId)
                .orElseThrow(() -> new RuntimeException("Order not found for eventId: " + eventId));
    }

    public Order getOrderByOrderId(String orderId) {
        log.info("Fetching order for orderId: {}", orderId);
        return orderRepository.findByOrderId(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found for orderId: " + orderId));
    }
}
