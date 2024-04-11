package com.project.groups.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class PaymentApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaymentApplication.class, args);
    }

    @PostMapping("/processCardPayment")
    public String processCardPayment(@RequestBody CardPaymentRequest request) {
        // 클라이언트로부터 받은 카드 정보를 검증하고 결제를 처리하는 로직을 구현합니다.
        // 이 예시에서는 간단하게 받은 데이터를 출력하는 것으로 대체합니다.
        System.out.println("Card Number: " + request.getCardNumber());
        System.out.println("Expiration Month: " + request.getExpirationMonth());
        System.out.println("Expiration Year: " + request.getExpirationMonth());
        //System.out.println("CCV: " + request.getCcv());

        // 여기에 실제 결제 처리 로직을 추가하세요.

        return "결제가 성공적으로 처리되었습니다.";
    }
}

class CardPaymentRequest {
    private String cardNumber;
    private String expirationMonth;
    private String expirationYear;
    private String ccv;
	public String getCardNumber() {
		// TODO Auto-generated method stub
		return null;
	}
	public String getExpirationMonth() {
		// TODO Auto-generated method stub
		return null;
	}

    // 게터와 세터는 생략했습니다. 필요한 경우 구현하세요.
}