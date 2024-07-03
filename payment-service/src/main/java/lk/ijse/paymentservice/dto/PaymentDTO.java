package lk.ijse.paymentservice.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaymentDTO implements SuperDTO{
    private String payment_code;
    private String ticket_id;
    private LocalDate payment_date;
    private Time paymet_time;
    private double amount;
    private String payment_type;
}
