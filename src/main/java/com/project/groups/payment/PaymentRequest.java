package com.project.groups.payment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {
    private String name;
    private String imp_uid;
    private String merchant_uid;
    private String buyer_name;
    private String buyer_email;
    private String buyer_addr;
    private Integer using_term;
    private Integer amount;
//    private Timestamp payment_time;
}
