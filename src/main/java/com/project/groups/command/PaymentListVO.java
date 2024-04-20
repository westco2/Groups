package com.project.groups.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentListVO {
    private String login_id;
    private String korn_flnm;
    private Timestamp pay_date;
    private Timestamp end_date;
    private String pay_product;
    private Integer using_term;
    private Integer pay_money;
}
