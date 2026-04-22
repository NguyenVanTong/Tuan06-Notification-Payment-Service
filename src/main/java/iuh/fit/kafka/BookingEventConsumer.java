package iuh.fit.kafka;


import iuh.fit.event.EventMessage;
import iuh.fit.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class BookingEventConsumer {
    private static final Logger logger = LoggerFactory.getLogger(BookingEventConsumer.class);
    private final PaymentService paymentService;

    public BookingEventConsumer(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @KafkaListener(topics = "booking.events", groupId = "payment-service")
    public void listen(EventMessage event) {
        logger.info("Received event: {}", event);
        if ("BOOKING_CREATED".equals(event.getEventType())
                && event.getData() != null
                && event.getData().getBookingId() != null) {
            Long bookingId = event.getData().getBookingId();
            paymentService.processPayment(bookingId);
        } else {
            logger.debug("Ignoring non-BOOKING_CREATED or invalid event: {}", event);
        }
    }
}
