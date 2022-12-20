package me.hanhyur.pay.user.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    public enum PaymentMethods {
        BANK, CARD
    }

    private String userId;

    private String email;

    private Set<PaymentMethods> paymentMethods;

    private Set<String> cardCompany;

    private Set<String> bankCompany;

    private Long payMoney;

    private Long point;

}
