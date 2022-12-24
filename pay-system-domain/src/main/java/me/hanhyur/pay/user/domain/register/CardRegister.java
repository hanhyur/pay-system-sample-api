package me.hanhyur.pay.user.domain.register;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardRegister implements RegisterForm {

    private String cardCompany;

    private String cardNumber;

    private int year;

    private int month;

    private int cvc;

    @Override
    public String getCompanyName() {
        return cardCompany;
    }

}
