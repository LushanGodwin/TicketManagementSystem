package lk.ijse.userservice.entity;

import jakarta.persistence.*;
import jdk.jfr.DataAmount;
import lk.ijse.userservice.Enum.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Table(name = "user")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    private String userCode;
    private String name;
    private String email;
    private String contact;
    private String address;
    private String nic;
    private LocalDate registrationDate;
    @Enumerated(EnumType.STRING)
    private Status status = Status.AVAILABLE;
}
