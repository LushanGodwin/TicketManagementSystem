package lk.ijse.userservice.service.impl;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import lk.ijse.userservice.dto.UserDTO;
import lk.ijse.userservice.entity.UserEntity;
import lk.ijse.userservice.repository.UserDao;
import lk.ijse.userservice.service.UserService;
import lk.ijse.userservice.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final Mapping mapping;
    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        userDTO.setUserCode(generateUserCode());
        return mapping.toUserDTO(java.util.Optional.of(userDao.save(mapping.toUserEntity(userDTO))));

    }

    @Override
    public UserDTO getUser(String id) {
        if (!userDao.existsById(id)) throw new NotFoundException("User not Found");
        return mapping.toUserDTO(userDao.findById(id));
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return mapping.toUserDTOList(userDao.findAll());
    }

    @Override
    public boolean deleteUser(String id) {
        Optional<UserEntity> userEntity = userDao.findById(id);
        if (userEntity.isPresent()){
            userDao.delete(userEntity.get());
            return true;
        }
        return false;
    }

    @Override
    public void updateUser(String id, UserDTO userDTO) {
       if (!userDao.existsById(id)) throw new NotFoundException("User already exists");
       userDTO.setUserCode(id);
       userDao.save(mapping.toUserEntity(userDTO));
    }

    @Override
    public boolean isUserExists(String userId) {
        return userDao.existsById(userId);
    }

    private String generateUserCode() {
        UserEntity firstByOrderByUserCodeDesc = userDao.findFirstByOrderByUserCodeDesc();
        return (firstByOrderByUserCodeDesc != null)
                ? String.format("User-%03d", Integer.parseInt(firstByOrderByUserCodeDesc.getUserCode().replace("User-", "")) + 1)
                : "User-001";
    }
}
