package lk.ijse.paymentservice.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.paymentservice.dto.PaymentDTO;
import lk.ijse.paymentservice.entity.PaymentEntity;
import lk.ijse.paymentservice.repository.PaymentRepo;
import lk.ijse.paymentservice.service.PaymentService;
import lk.ijse.paymentservice.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PaymentServiceIMPL implements PaymentService<PaymentDTO,String> {
    private final PaymentRepo paymentRepo;
    private final Mapping mapping;

    @Override
    public void save(PaymentDTO paymentDTO) {

       paymentRepo.save(mapping.toPaymentEntity(paymentDTO));


    }

    @Override
    public void update(PaymentDTO paymentDTO) {
        boolean isExist = paymentRepo.existsById(paymentDTO.getPayment_code());
        if (!isExist) {
           return;
        }
        paymentRepo.save(mapping.toPaymentEntity(paymentDTO));
    }

    @Override
    public PaymentDTO search(String payment_code) {
        return null;
    }

    @Override
    public List<PaymentDTO> getAll() {
        return null;
    }
}
