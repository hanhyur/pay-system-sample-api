package me.hanhyur.pay.user.service.register;

import lombok.extern.slf4j.Slf4j;
import me.hanhyur.pay.user.domain.register.RegisterForm;
import me.hanhyur.pay.user.service.handler.card.CardRegisterHandler;
import me.hanhyur.pay.user.service.handler.card.HyeonDayCardRegisterHandler;
import me.hanhyur.pay.user.service.handler.card.ShimHamCardRegisterHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CardCompanyConnector implements Connector {

    private CardRegisterHandler cardRegisterHandler;

    public void setCompany(RegisterForm registerForm) {
        if (registerForm.getCompanyName().equals("HyeonDay")) {
            this.cardRegisterHandler = new HyeonDayCardRegisterHandler();
        } else if (registerForm.getCompanyName().equals("ShimHam")) {
            this.cardRegisterHandler = new ShimHamCardRegisterHandler();
        }
    }

    public boolean isValid(RegisterForm registerForm) {
        log.info("Card Validating... 1s");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return cardRegisterHandler.isValidCard(registerForm);
    }

    public boolean isRegistered(RegisterForm registerForm) {
        log.info("Card Registering... 1s");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return cardRegisterHandler.registeredCard(registerForm);
    }

}
