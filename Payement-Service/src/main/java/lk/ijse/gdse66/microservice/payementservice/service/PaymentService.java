package lk.ijse.gdse66.microservice.payementservice.service;

import lk.ijse.gdse66.microservice.payementservice.dto.PaymentDTO;

import java.util.List;


public interface PaymentService {
    List<PaymentDTO> getAllPayment();
    PaymentDTO getPaymentDetails(String payment_id);
    PaymentDTO savePayment(PaymentDTO paymentDTO);
    void updatePayment(String payment_id, PaymentDTO paymentDTO);
    void deletePayment(String payment_id);
}
