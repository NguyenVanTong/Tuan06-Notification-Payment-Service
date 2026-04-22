package iuh.fit.service;



import iuh.fit.entity.Payment;
import iuh.fit.kafka.PaymentEventProducer;
import iuh.fit.repository.PaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentService {
    private static final Logger logger = LoggerFactory.getLogger(PaymentService.class);
    private final PaymentRepository paymentRepository;
    private final PaymentEventProducer paymentEventProducer;

    public PaymentService(PaymentRepository paymentRepository, PaymentEventProducer paymentEventProducer) {
        this.paymentRepository = paymentRepository;
        this.paymentEventProducer = paymentEventProducer;
    }

    @Transactional
    public void processPayment(Long bookingId) {
        logger.info("Processing payment for bookingId={}", bookingId);
        boolean success = Math.random() > 0.5;
        String status = success ? "PAYMENT_COMPLETED" : "BOOKING_FAILED";

        Payment payment = new Payment();
        payment.setBookingId(bookingId);
        payment.setStatus(status);
        paymentRepository.save(payment);

        paymentEventProducer.publishEvent(status, bookingId);
    }
}
