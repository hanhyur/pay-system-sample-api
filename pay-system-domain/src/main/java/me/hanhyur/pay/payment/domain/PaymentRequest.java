package me.hanhyur.pay.payment.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public record PaymentRequest(String userId,
                             String shopId,
                             Long money,
                             boolean usingPoint,
                             Long usedPoint,
                             LocalDateTime dateTime) {

//    private String userId;
//
//    private String shopId;
//
//    private Long money;
//
//    private boolean usingPoint;
//
//    private Long usedPoint;
//
//    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
//    private LocalDateTime dateTime;

}
