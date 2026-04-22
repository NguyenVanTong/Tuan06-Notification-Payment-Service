package iuh.fit.service;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import iuh.fit.event.EventMessage;

@Service
public class NotificationService {
    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    @KafkaListener(topics = "payment.events", groupId = "notification-service")
    public void listen(EventMessage event) {
        logger.info("Received event: {}", event);
        if ("PAYMENT_COMPLETED".equals(event.getEventType())
                && event.getData() != null
                && event.getData().getBookingId() != null) {
            Long bookingId = event.getData().getBookingId();
            String message = String.format("Booking #%d thành công!", bookingId);
            System.out.println(message);
            logger.info("Notification output: {}", message);
        } else {
            logger.debug("No notification sent for eventType={}", event.getEventType());
        }
    }
}