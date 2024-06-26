package myswamp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@DataJpaTest
@EnableAutoConfiguration
@ContextConfiguration(classes = OutboxEventRepository.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
public class OutboxEventRepositoryTest {

    @Autowired
    private OutboxEventRepository outboxEventRepository;

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15-alpine");


    @Test
    public void testOutboxEventSaveAndFindByAggregateId() {

        OutboxEvent outboxEvent = new OutboxEvent("order123",
                "OrderCreated",
                "Order",
                "payload".getBytes(),
                System.currentTimeMillis());

        outboxEventRepository.save(outboxEvent);

        Optional<OutboxEvent> result = outboxEventRepository.findByAggregateId("order123");
        assertTrue(result.isPresent());

        OutboxEvent outboxEventFromDb = result.get();

        assertEquals("Order", outboxEventFromDb.getAggregateType());
        assertEquals("OrderCreated", outboxEventFromDb.getAggregateSubType());
        assertEquals("payload", new String(outboxEventFromDb.getPayload(), StandardCharsets.UTF_8));

    }

}