package iuh.fit.kafka;


import iuh.fit.event.EventData;
import iuh.fit.event.EventMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class PaymentEventProducer {
    private static final Logger logger = LoggerFactory.getLogger(PaymentEventProducer.class);
    private final KafkaTemplate<String, EventMessage> kafkaTemplate;

    public PaymentEventProducer(KafkaTemplate<String, EventMessage> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishEvent(String eventType, Long bookingId) {
        EventMessage event = new EventMessage(eventType, new EventData(bookingId));

        logger.info("Publishing result: {}", event);
        kafkaTemplate.send("payment.events", event);
    }
}
