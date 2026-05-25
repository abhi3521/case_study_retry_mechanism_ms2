package com.case_study.ms2.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderEvent {
    private String eventId;
    private String orderId;
    private String customerId;
    private Double amount;
    private String status;
}
