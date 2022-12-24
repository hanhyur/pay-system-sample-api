package me.hanhyur.pay.user.service.handler.card;

import me.hanhyur.pay.user.domain.register.RegisterForm;

public interface CardRegisterHandler {

    boolean isValidCard(RegisterForm registerForm);

    boolean registeredCard(RegisterForm registerForm);

}
