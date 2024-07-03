package lk.ijse.paymentservice.service;


import lk.ijse.paymentservice.dto.PaymentDTO;

import java.util.List;

public interface PaymentService<T,ID> {
    void save(T dto);
    void update(T dto);
    T search(ID payment_code);

    List<T> getAll();

}
