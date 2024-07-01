package lk.ijse.userservice.service;

import lk.ijse.userservice.dto.UserDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    UserDTO saveUser(UserDTO userDTO);

    UserDTO getUser(String id);

    List<UserDTO> getAllUsers();

    boolean deleteUser(String id);

    void updateUser(String id, UserDTO userDTO);

    boolean isUserExists(String userId);
}
