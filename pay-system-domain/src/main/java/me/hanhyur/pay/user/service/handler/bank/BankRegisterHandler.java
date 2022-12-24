package me.hanhyur.pay.user.service.handler.bank;

import me.hanhyur.pay.user.domain.register.RegisterForm;

public interface BankRegisterHandler {

    boolean isValidAccount(RegisterForm registerForm);

    boolean registeredAccount(RegisterForm registerForm);

}
