package me.hanhyur.pay.user.service.charge;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ChargeService {

    private Charge charge;

    public void setChargeMethod(Charge charge) {
        this.charge = charge;
    }

    public Long chargeMoney(String userId, Long money) {
        if (charge.isRegistered(userId)) {
            return charge.charge(userId, money);
        } else {
            log.info("Not Registered");

            return -1L;
        }
    }

}
