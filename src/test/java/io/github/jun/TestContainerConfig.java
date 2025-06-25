package io.github.jun;

import com.redis.testcontainers.RedisContainer;
import org.junit.jupiter.api.AfterAll;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.lifecycle.Startables;
import org.testcontainers.utility.DockerImageName;

public abstract class TestContainerConfig {

    private static final DockerImageName postgresImageName = DockerImageName.parse("postgres:16-bullseye");
    private static final DockerImageName kafkaImageName = DockerImageName.parse("confluentinc/cp-kafka:7.3.1");
    private static final DockerImageName redisImageName = DockerImageName.parse("redis:7.0.14");

    private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(postgresImageName)
            .withDatabaseName("etldb")
            .withUsername("postgres")
            .withPassword("password");
    private static final KafkaContainer rabbitMQ = new KafkaContainer(kafkaImageName);
    private static final RedisContainer redis = new RedisContainer(redisImageName);

    @AfterAll
    static void afterAllBase() {
        postgres.stop();
        rabbitMQ.stop();
        redis.stop();
    }

    @DynamicPropertySource
    static void databaseProperties(DynamicPropertyRegistry registry) {
        Startables.deepStart(postgres, rabbitMQ, redis).join();

        registry.add("spring.datasource.url", () ->
                "jdbc:postgresql://" + postgres.getHost() + ":" + postgres.getFirstMappedPort() + "/etldb"
        );
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);

        registry.add("spring.redis.host", redis::getHost);
        registry.add("spring.redis.port", redis::getFirstMappedPort);
    }
}
