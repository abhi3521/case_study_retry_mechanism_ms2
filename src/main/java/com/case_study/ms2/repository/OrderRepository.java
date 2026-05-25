package com.case_study.ms2.repository;

import com.case_study.ms2.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByEventId(String eventId);

    Optional<Order> findByOrderId(String orderId);
}
