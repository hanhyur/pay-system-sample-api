package me.hanhyur.pay.payment.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.hanhyur.pay.payment.domain.PaymentRequest;
import me.hanhyur.pay.payment.domain.PaymentResponse;
import me.hanhyur.pay.payment.domain.Shop;
import me.hanhyur.pay.persistence.repository.ShopRepository;
import me.hanhyur.pay.persistence.repository.TransactionRepository;
import me.hanhyur.pay.persistence.repository.UserRepository;
import me.hanhyur.pay.user.domain.Transaction;
import me.hanhyur.pay.user.domain.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentService {

    private final ShopRepository shopRepository;
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;
    private final PointValidator pointValidator;

    public PaymentResponse doPayment(PaymentRequest paymentRequest) {
        updateTransactionRecord(paymentRequest, transactionRepository);

        User userResult = updateUser(paymentRequest, validatePointAndGetMoney(paymentRequest, pointValidator), userRepository);
        Shop shopResult = updateShop(paymentRequest, shopRepository);

        return PaymentResponse.builder()
                .userId(paymentRequest.getUserId())
                .shopName(shopResult.getShopName())
                .money(userResult.getPayMoney())
                .build();
    }

    private static Long validatePointAndGetMoney(PaymentRequest paymentRequest, PointValidator pointValidator) {
        if (!paymentRequest.isUsingPoint()) {
            return paymentRequest.getMoney();
        }

        if (!pointValidator.validateUserPoint(paymentRequest.getUserId(), paymentRequest.getUsedPoint())) {
            return paymentRequest.getMoney();
        }

        return paymentRequest.getMoney() - paymentRequest.getUsedPoint();
    }

    private static void updateTransactionRecord(PaymentRequest paymentRequest, TransactionRepository transactionRepository) {
        Transaction trx = Transaction.builder()
                .paymentId(transactionIdGenerator(paymentRequest.getDateTime()))
                .myId(paymentRequest.getUserId())
                .otherId(paymentRequest.getShopId())
                .trxDate(paymentRequest.getDateTime())
                .trxType(Transaction.TrxType.PAYMENT)
                .money(paymentRequest.getMoney())
                .build();
    }

    private static String transactionIdGenerator(LocalDateTime dateTime) {
        return String.valueOf(dateTime.getYear())
                + dateTime.getMonthValue()
                + dateTime.getDayOfMonth()
                + UUID.randomUUID().toString().substring(0, 7);
    }

    private static User updateUser(PaymentRequest paymentRequest, Long toPay, UserRepository userRepository) {
        User result = userRepository.findById(paymentRequest.getUserId()).orElseThrow(NoSuchElementException::new);

        result.setPayMoney(result.getPayMoney() - toPay);
        result.setPoint(result.getPoint() - paymentRequest.getUsedPoint());

        return userRepository.save(result);
    }

    private static Shop updateShop(PaymentRequest paymentRequest, ShopRepository shopRepository) {
        Shop result = shopRepository.findById(paymentRequest.getShopId()).orElseThrow(NoSuchElementException::new);

        result.setPayMoney(result.getPayMoney() + paymentRequest.getUsedPoint());

        return shopRepository.save(result);
    }

}
