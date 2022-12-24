package me.hanhyur.pay.user.domain;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    public enum PaymentMethods {
        BANK_ACCOUNT, CARD
    }

    @Id
    private String userId;

    private String email;

    private Set<PaymentMethods> paymentMethods;

    private Set<String> cardCompany;

    private Set<String> bankCompany;

    private Long payMoney;

    private Long point;

}
