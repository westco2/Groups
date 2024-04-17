package com.project.groups.payment;

import com.project.groups.command.PaymentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "PaymentService")
public class PaymentServiceImpl implements PaymentService{

    @Autowired
    public PaymentMapper paymentMapper;
    @Override
    public void tier_update(String buyer_name, String name) {
        paymentMapper.tier_updatemap(buyer_name, name);
    }

    @Override
    public int payment_content(PaymentVO paymentVO) {
        return paymentMapper.payment_content(paymentVO);
    }
}
