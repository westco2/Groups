package com.project.groups.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.groups.command.PaymentVO;
import com.project.groups.payment.PaymentRequest;
import com.project.groups.payment.PaymentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.Timestamp;


@Controller
@RequestMapping("/paymentpageZ")
public class PaymentZController {

    private final PaymentService paymentService;
    private final ObjectMapper objectMapper;

    public PaymentZController(PaymentService paymentService, ObjectMapper objectMapper) {
        this.paymentService = paymentService;
        this.objectMapper = objectMapper;
    }

//    @GetMapping("/topaymentpageZ")
//    public String showPaymentPage() {
//        return "paymentpageZ/topaymentpageZ";
//    }


    @PostMapping("/topaymentpageZ")
    public void getpaymentinf(@RequestBody String requestBody, Model model, PaymentVO paymentVO) throws IOException {
        // 요청 본문의 JSON 데이터를 객체로 파싱
        PaymentRequest paymentRequest = objectMapper.readValue(requestBody, PaymentRequest.class);
        paymentVO = objectMapper.readValue(requestBody, PaymentVO.class);
        // 파싱된 데이터 사용
        String name = paymentRequest.getName(); //결제 티어 이름
        String imp_uid = paymentRequest.getImp_uid(); //포트원 고유 결제 번호 결제시 자동으로 정해지는듯
        String merchant_uid = paymentRequest.getMerchant_uid(); //결제 고유 번호
        String buyer_name = paymentRequest.getBuyer_name(); //구매자 아이디
        String buyer_email = paymentRequest.getBuyer_email(); //구매자 이메일
        String buyer_addr = paymentRequest.getBuyer_addr(); //구매자 주소
        Integer using_term = paymentRequest.getUsing_term();
        Integer amount = paymentRequest.getAmount();
        Timestamp payment_time = new Timestamp(System.currentTimeMillis());
        paymentVO.setPayment_time(payment_time);
        // 모델에 데이터 추가, 모델에 추가를 해버렸는데 막상 필요없는거 같기도함
        model.addAttribute("name", name);
        model.addAttribute("imp_uid", imp_uid);
        model.addAttribute("merchant_uid", merchant_uid);
        model.addAttribute("buyer_name", buyer_name);
        model.addAttribute("buyer_email", buyer_email);
        model.addAttribute("buyer_addr", buyer_addr);

        // 로그 출력(잘되는지 테스트)
        System.out.println("name = " + name); //결제 상품 이름, 티어 이름, *사람 이름이 아님
        System.out.println("imp_uid = " + imp_uid);
        System.out.println("buyer_name = " + buyer_name);
        System.out.println("buyer_addr = " + buyer_addr);
        System.out.println("buyer_email = " + buyer_email);
        System.out.println("merchant_uid = " + merchant_uid);
        System.out.println("using_term = " + using_term);
        System.out.println("payment_time = " + payment_time);
        System.out.println("amount = " + amount);
        System.out.println("paymentVO = " + paymentVO);


        paymentService.tier_update(buyer_name, name); //해당 구매자 아이디로 티어를 올림
        if(paymentService.payment_content(paymentVO) > 0){
            System.out.println("결제 데이터 삽입 성공");
        } else {
            System.out.println("실패!");
        };
    }

}
