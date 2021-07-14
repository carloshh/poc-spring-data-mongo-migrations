package com.github.carloshh.poc.mongo.repository;

import com.github.carloshh.poc.mongo.domain.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccountRepository extends MongoRepository<Account, UUID> {
}
