package me.hanhyur.pay.payment.domain;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Shop {

    @Id
    private String shopId;

    private String shopName;

    private Long payMoney;

}
