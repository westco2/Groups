package com.project.groups.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentListVO {
    private String login_id;
    private String korn_flnm;
    private LocalDateTime pay_date;
    private LocalDateTime end_date;
    private String pay_product;
    private Integer using_term;
    private Integer pay_money;
}
