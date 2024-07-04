package lk.ijse.gdse66.microservice.payementservice.service.impl;

import lk.ijse.gdse66.microservice.payementservice.dto.PaymentDTO;
import lk.ijse.gdse66.microservice.payementservice.persistance.entity.Payment;
import lk.ijse.gdse66.microservice.payementservice.persistance.repository.PaymentRepository;
import lk.ijse.gdse66.microservice.payementservice.service.PaymentService;
import lk.ijse.gdse66.microservice.payementservice.service.execption.DublicateRecordException;
import lk.ijse.gdse66.microservice.payementservice.service.execption.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {
    PaymentRepository paymentRepository;
    ModelMapper modelMapper;

    public PaymentServiceImpl(PaymentRepository paymentRepository, ModelMapper modelMapper) {
        this.paymentRepository = paymentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<PaymentDTO> getAllPayment() {
        return paymentRepository.findAll().stream().map(
                payment -> modelMapper.map(payment,PaymentDTO.class)
        ).toList();
    }

    @Override
    public PaymentDTO getPaymentDetails(String payment_id) {
        if(!paymentRepository.existsByPaymentId(payment_id)){
            throw new NotFoundException("Payment "+payment_id+" Not Found!");
        }
        return modelMapper.map(paymentRepository.findByPaymentId(payment_id), PaymentDTO.class);
    }

    @Override
    public PaymentDTO savePayment(PaymentDTO paymentDTO) {
        if(paymentRepository.existsByPaymentId(paymentDTO.getPaymentId())){
            throw new DublicateRecordException("This Payment "+paymentDTO.getPaymentId()+" already exicts...");
        }
        return modelMapper.map(paymentRepository.save(modelMapper.map(
                paymentDTO, Payment.class)), PaymentDTO.class
        );
    }

    @Override
    public void updatePayment(String payment_id, PaymentDTO paymentDTO) {
        if(!paymentRepository.existsByPaymentId(payment_id)){
            throw new NotFoundException("Payment"+ payment_id + "Not Found...");
        }
        paymentDTO.setPaymentId(payment_id);
        paymentRepository.save(modelMapper.map(paymentDTO,Payment.class));
    }

    @Override
    public void deletePayment(String payment_id) {
        if(!paymentRepository.existsByPaymentId(payment_id)){
            throw  new NotFoundException("Payment"+ payment_id + "Not Found...");
        }
        paymentRepository.deleteByPaymentId(payment_id);
    }
}
