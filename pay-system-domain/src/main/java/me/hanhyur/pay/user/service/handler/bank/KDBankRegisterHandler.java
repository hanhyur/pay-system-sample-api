package me.hanhyur.pay.user.service.handler.bank;

import lombok.extern.slf4j.Slf4j;
import me.hanhyur.pay.user.domain.register.RegisterForm;

@Slf4j
public class KDBankRegisterHandler implements BankRegisterHandler {

    @Override
    public boolean isValidAccount(RegisterForm registerForm) {
        log.info("this KDBank Account is valid");

        return true;
    }

    @Override
    public boolean registeredAccount(RegisterForm registerForm) {
        log.info("KDBank Account is registered");

        return true;
    }

}
