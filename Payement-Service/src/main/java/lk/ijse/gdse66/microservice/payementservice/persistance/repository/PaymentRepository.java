package lk.ijse.gdse66.microservice.payementservice.persistance.repository;

import lk.ijse.gdse66.microservice.payementservice.persistance.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment,String> {
    Payment findByPaymentId(String paymentId);
    Boolean existsByPaymentId(String paymentId);
    void deleteByPaymentId(String paymentId);
}
