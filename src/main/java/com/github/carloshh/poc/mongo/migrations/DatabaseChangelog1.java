package com.github.carloshh.poc.mongo.migrations;

import com.github.carloshh.poc.mongo.domain.Account;
import com.github.carloshh.poc.mongo.repository.AccountRepository;
import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.github.cloudyrock.mongock.driver.mongodb.springdata.v3.decorator.impl.MongockTemplate;

import java.util.UUID;

import static com.mongodb.client.model.Filters.empty;
import static com.mongodb.client.model.Filters.eq;

@ChangeLog(order = "001")
public class DatabaseChangelog1 {
    public static final String COLLECTION_ACCOUNT = "account";

    @ChangeSet(order = "001", id = "create collection", author = "carloshh")
    public void createCollection(MongockTemplate mongockTemplate) {
        mongockTemplate.createCollection(COLLECTION_ACCOUNT);
    }

    @ChangeSet(order = "002", id = "create account", author = "carloshh")
    public void createAccount(AccountRepository accountRepository) {
        var user = new Account(UUID.fromString("c31ffc7c-b131-454f-913d-c13b36a20700"), "Carlos", null);
        accountRepository.save(user);
    }

    @ChangeSet(order = "003", id = "Add new field to all accounts", author = "carloshh")
    public void addNewFieldToAllAccounts(MongockTemplate mongockTemplate) {
        mongockTemplate.getCollection(COLLECTION_ACCOUNT).updateMany(empty(), eq("$set", eq("enabled", true)));
    }

}
