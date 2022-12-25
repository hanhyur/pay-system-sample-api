package me.hanhyur.pay.user.service.register;

import lombok.extern.slf4j.Slf4j;
import me.hanhyur.pay.user.domain.register.RegisterForm;
import me.hanhyur.pay.user.service.handler.bank.BMKBankRegisterHandler;
import me.hanhyur.pay.user.service.handler.bank.BankRegisterHandler;
import me.hanhyur.pay.user.service.handler.bank.KDBankRegisterHandler;
import me.hanhyur.pay.user.service.handler.card.HyeonDayCardRegisterHandler;
import me.hanhyur.pay.user.service.handler.card.ShimHamCardRegisterHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BankAccountConnector implements Connector {

    private BankRegisterHandler bankRegisterHandler;

    public void setCompany(RegisterForm registerForm) {
        if (registerForm.getCompanyName().equals("BMK")) {
            this.bankRegisterHandler = new BMKBankRegisterHandler();
        } else if (registerForm.getCompanyName().equals("KD")) {
            this.bankRegisterHandler = new KDBankRegisterHandler();
        }
    }

    public boolean isValid(RegisterForm registerForm) {
        log.info("Account Validating... 1s");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return bankRegisterHandler.isValidAccount(registerForm);
    }

    public boolean isRegistered(RegisterForm registerForm) {
        log.info("Account Registering... 1s");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return bankRegisterHandler.registeredAccount(registerForm);
    }

}
