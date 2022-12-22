package me.hanhyur.pay.persistence.repository;

import me.hanhyur.pay.user.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
