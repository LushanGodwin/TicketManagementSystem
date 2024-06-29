package lk.ijse.userservice.util;

import lk.ijse.userservice.dto.UserDTO;
import lk.ijse.userservice.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class Mapping {
    private final ModelMapper modelMapper;

    public UserDTO toUserDTO(Optional<UserEntity> userEntity) {
        return modelMapper.map(userEntity, UserDTO.class);
    }

    public UserEntity toUserEntity(UserDTO userDTO){
        return modelMapper.map(userDTO, UserEntity.class);
    }

    public List<UserDTO> toUserDTOList(List<UserEntity> userEntities){
        return modelMapper.map(userEntities, List.class);
    }
}
