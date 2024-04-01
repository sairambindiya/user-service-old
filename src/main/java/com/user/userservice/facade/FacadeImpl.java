package com.user.userservice.facade;

import com.user.userservice.dto.UserDto;
import com.user.userservice.entity.UserEntity;
import org.springframework.stereotype.Service;

@Service
public interface FacadeImpl {

    UserEntity createUser(UserDto userDto);

    UserEntity updateUser(Long userId, UserDto userDto);

    UserDto getUser(Long userId);

    String deleteUser(Long userId);
}
