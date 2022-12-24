package me.hanhyur.pay.user.service.handler.card;

import lombok.extern.slf4j.Slf4j;
import me.hanhyur.pay.user.domain.register.RegisterForm;

@Slf4j
public class HyeonDayCardRegisterHandler implements CardRegisterHandler {

    @Override
    public boolean isValidCard(RegisterForm registerForm) {
        log.info("this HyeonDayCard is valid");

        return true;
    }

    @Override
    public boolean registeredCard(RegisterForm registerForm) {
        log.info("HyeonDayCard is registered!");

        return true;
    }

}
