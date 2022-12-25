package me.hanhyur.pay.user.service.handler.bank;

import lombok.extern.slf4j.Slf4j;
import me.hanhyur.pay.user.domain.register.RegisterForm;

@Slf4j
public class BMKBankRegisterHandler implements BankRegisterHandler{

    @Override
    public boolean isValidAccount(RegisterForm registerForm) {
        log.info("this BMKBank Account is valid");

        return true;
    }

    @Override
    public boolean registeredAccount(RegisterForm registerForm) {
        log.info("BMKBank Account is registered");

        return true;
    }

}
