package lk.ijse.paymentservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name= "payment")
public class PaymentEntity {
    @Id
    private String payment_code;
    private String ticket_id;
    private LocalDate payment_date;
    private Time paymet_time;
    private double amount;
    private String payment_type;
}
