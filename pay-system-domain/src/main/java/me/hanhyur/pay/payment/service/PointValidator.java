package me.hanhyur.pay.payment.service;

import me.hanhyur.pay.persistence.repository.UserRepository;
import me.hanhyur.pay.user.domain.User;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
public class PointValidator {
    private final UserRepository userRepository;

    public PointValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean validateUserPoint(String userId, Long point) {
        User result = userRepository.findById(userId).orElseThrow(NoSuchElementException::new);

        return result.getPoint() > point;
    }

}
