package me.hanhyur.pay.user.domain;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    public enum TrxType {
        PAYMENT, TRANSFER
    }

    @Id
    private String paymentId;

    private TrxType trxType;

    private String myId;

    private String otherId;

    private Long money;

    private LocalDateTime trxDate;

    private boolean isComplete;

}
