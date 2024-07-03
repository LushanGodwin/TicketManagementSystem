package lk.ijse.userservice.dto;

import lk.ijse.userservice.Enum.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String userCode;
    private String password;
    private String name;
    private String email;
    private String contact;
    private String address;
    private String nic;
    private LocalDate registrationDate;
    private Status status = Status.AVAILABLE;
}
