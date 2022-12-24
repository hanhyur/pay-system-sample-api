package me.hanhyur.pay.user.domain.register;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BankRegister implements RegisterForm {

    private String bankCompany;

    private String accountNumber;

    @Override
    public String getCompanyName() {
        return bankCompany;
    }

}
