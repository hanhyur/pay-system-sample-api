package me.hanhyur.pay.persistence.repository;

import me.hanhyur.pay.user.domain.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TransactionRepository extends MongoRepository<Transaction, String> {

    List<Transaction> findAllByMyId(String myId);

}
