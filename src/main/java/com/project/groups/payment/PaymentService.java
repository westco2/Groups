package com.project.groups.payment;

import com.project.groups.command.PaymentListVO;
import com.project.groups.command.PaymentVO;

import java.util.List;

public interface PaymentService {

    void tier_update(String buyer_name, String name);

    int payment_content(PaymentVO paymentVO);

    List<PaymentListVO> paymentlistcheck();
}
