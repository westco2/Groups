package com.project.groups.payment;

import com.project.groups.command.PaymentListVO;
import com.project.groups.command.PaymentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "PaymentService")
public class PaymentServiceImpl implements PaymentService{

    @Autowired
    public PaymentMapper paymentMapper;
    @Override
    public void tier_update(String buyer_name, String name) {
        String Rolename = "ROLE_TEACHER_" + name;
        System.out.println("결제 후 ROLE name = " + Rolename);
        paymentMapper.tier_updatemap(buyer_name, name, Rolename);
    }

    @Override
    public int payment_content(PaymentVO paymentVO) {
        return paymentMapper.payment_content(paymentVO);
    }

    @Override
    public List<PaymentListVO> paymentlistcheck() {
        return paymentMapper.paymentlistcheck();
    }
}
