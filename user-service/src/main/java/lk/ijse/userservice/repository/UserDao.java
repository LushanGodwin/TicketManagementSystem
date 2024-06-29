package lk.ijse.userservice.repository;

import lk.ijse.userservice.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<UserEntity,String> {
    UserEntity findFirstByOrderByUserCodeDesc();
}
