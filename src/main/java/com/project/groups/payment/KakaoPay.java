package com.project.groups.payment;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class KakaoPay {
    public static void main(String[] args) {
        try {
            // 카카오페이 API 엔드포인트 URL
            URL url = new URL("https://kapi.kakao.com/v1/payment/ready");

            // API 호출을 위한 HTTP 연결 설정
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Authorization", "Bearer {YOUR_KAKAOPAY_REST_API_KEY}");

            // POST 요청 데이터 설정
            Map<String, String> postData = new HashMap<>();
            postData.put("item_name", "{상품명}");
            postData.put("amount", "{결제금액}");
            postData.put("buyer_name", "{구매자이름}");
            postData.put("buyer_email", "{구매자이메일}");
            postData.put("buyer_tel", "{구매자전화번호}");
            postData.put("carrier", "{통신사}");
            String postDataString = "";

            for (Map.Entry<String, String> entry : postData.entrySet()) {
                if (!postDataString.isEmpty()) {
                    postDataString += "&";
                }
                postDataString += entry.getKey() + "=" + entry.getValue();
            }

            // POST 요청 데이터 전송
            connection.setDoOutput(true);
            OutputStream os = connection.getOutputStream();
            byte[] input = postDataString.getBytes("utf-8");
            os.write(input, 0, input.length);

            // API 응답 수신
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }

            // API 응답 출력
            System.out.println(response.toString());

            // 연결 종료
            os.close();
            br.close();
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}