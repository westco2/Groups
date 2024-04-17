package com.project.groups.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentVO {
    private String name; //결제 티어 이름(BASIC, MASTER)
    private String imp_uid; //포트원 고유 결제 번호 결제시 자동으로 정해지는듯 용도 모르겠네
    private String merchant_uid; //결제고유번호
    private String buyer_name; //구매자 아이디
    private String buyer_email; //구매자 이메일
    private String buyer_addr; //구매자 주소
    private Integer using_term; //구독 개월
    private Integer amount; //결제 금액
    private Timestamp payment_time; //결제일자 pay_date
}
