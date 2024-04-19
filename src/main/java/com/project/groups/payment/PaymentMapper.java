package com.project.groups.payment;

import com.project.groups.command.PaymentVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentMapper {

    void tier_updatemap(String buyer_name, String name, String Rolename);

    int payment_content(PaymentVO paymentVO);
}
