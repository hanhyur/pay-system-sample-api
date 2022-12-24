package me.hanhyur.pay.user.service.register;

import me.hanhyur.pay.user.domain.register.RegisterForm;

public interface Connector {

    void setCompany(RegisterForm registerForm);

    boolean isValid(RegisterForm registerForm);

    boolean isRegistered(RegisterForm registerForm);

}
