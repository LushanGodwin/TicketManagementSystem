package lk.ijse.paymentservice.util;

import lk.ijse.paymentservice.dto.PaymentDTO;
import lk.ijse.paymentservice.entity.PaymentEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class Mapping {
    private final ModelMapper mapper;

    public PaymentEntity toPaymentEntity(PaymentDTO paymentDTO) {
        return mapper.map(paymentDTO, PaymentEntity.class);
    }

    public PaymentDTO toPaymentDTO(PaymentEntity paymentEntity) {
        return mapper.map(paymentEntity, PaymentDTO.class);
    }
}
