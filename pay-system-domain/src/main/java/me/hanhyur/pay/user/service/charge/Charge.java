package me.hanhyur.pay.user.service.charge;

public interface Charge {

    boolean isRegistered(String userId);

    Long charge(String userId, Long money);

}
