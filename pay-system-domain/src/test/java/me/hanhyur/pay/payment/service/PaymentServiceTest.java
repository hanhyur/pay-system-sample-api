package me.hanhyur.pay.payment.service;

import lombok.extern.slf4j.Slf4j;
import me.hanhyur.pay.payment.domain.PaymentRequest;
import me.hanhyur.pay.payment.domain.PaymentResponse;
import me.hanhyur.pay.payment.domain.Shop;
import me.hanhyur.pay.persistence.repository.ShopRepository;
import me.hanhyur.pay.persistence.repository.UserRepository;
import me.hanhyur.pay.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
class PaymentServiceTest {

    @Mock
    private PaymentService paymentService;

    @Mock
    private ShopRepository shopRepository;
    
    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void addData() {
        User user1 = User.builder()
                .userId("hanhyur")
                .payMoney(50000L)
                .point(3000L)
                .build();

        Shop shop1 = Shop.builder()
                .shopId("1111")
                .shopName("StarBucks")
                .payMoney(0L)
                .build();

        userRepository.save(user1);
        shopRepository.save(shop1);
    }

    @Test
    void doPaymentTest() {
        String userId = "hanhyur";
        String shopId = "1111";
        Long money = 4800L;

        PaymentResponse result = paymentService.doPayment(
                new PaymentRequest(userId, shopId, money, true, 1000L, LocalDateTime.now()));

        assertEquals(userRepository.findById("hanhyur").get().getPayMoney(), result.getMoney());

        log.info("{} / {}", userRepository.findById("hanhyur").get().getPayMoney(), result.getMoney());
    }

}