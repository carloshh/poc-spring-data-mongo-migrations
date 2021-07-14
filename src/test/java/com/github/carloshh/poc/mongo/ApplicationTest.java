package com.github.carloshh.poc.mongo;

import com.github.carloshh.poc.mongo.repository.AccountRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Testcontainers
class ApplicationTest {

	@Container
	private static final MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.6");

	@DynamicPropertySource
	public static void setDatasourceProperties(final DynamicPropertyRegistry registry) {
		registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
	}

	@Autowired
	private AccountRepository accountRepository;

	@Test
	@DisplayName("Migrations executed successfully in tests")
	void testAccountMigration() {
		var user = accountRepository.findAll().stream().findFirst().orElseThrow();

		assertThat(user.enabled()).isTrue();
	}

}
