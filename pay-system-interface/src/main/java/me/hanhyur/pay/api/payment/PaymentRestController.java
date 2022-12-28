package me.hanhyur.pay.api.payment;

import lombok.RequiredArgsConstructor;
import me.hanhyur.pay.payment.domain.PaymentRequest;
import me.hanhyur.pay.payment.domain.PaymentResponse;
import me.hanhyur.pay.payment.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PaymentRestController {

    private final PaymentService paymentService;

    @PostMapping("/payment/paymentProcess")
    public ResponseEntity<PaymentResponse> doPayment(@RequestBody PaymentRequest paymentRequest) {
        PaymentResponse result = paymentService.doPayment(paymentRequest);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
