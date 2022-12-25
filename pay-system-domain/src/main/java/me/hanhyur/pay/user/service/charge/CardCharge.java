package me.hanhyur.pay.user.service.charge;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.hanhyur.pay.persistence.repository.UserRepository;
import me.hanhyur.pay.user.domain.User;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class CardCharge implements Charge {

    private final UserRepository userRepository;

    @Override
    public boolean isRegistered(String userId) {
        Set<User.PaymentMethods> result
                = userRepository.findById(userId).orElseThrow(NoSuchElementException::new).getPaymentMethods();

        return result.contains(User.PaymentMethods.CARD);
    }

    @Override
    public Long charge(String userId, Long money) {
        log.info("Charging From Card...");

        User result = userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
        result.setPayMoney(result.getPayMoney() + money);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info("Charging Complete");

        return userRepository.save(result).getPayMoney();
    }

}
