package me.hanhyur.pay.persistence.repository;

import me.hanhyur.pay.payment.domain.Shop;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShopRepository extends MongoRepository<Shop, String> {
}
