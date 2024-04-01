package com.user.userservice.facade;

import com.user.userservice.dto.UserDto;
import com.user.userservice.entity.UserEntity;
import com.user.userservice.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceFacade implements FacadeImpl{
    public UserServiceFacade(UserService userService) {
        this.userService = userService;
    }

    private UserService userService;
    @Override
    public UserEntity createUser(UserDto userDto) {
        return userService.createUser(convertDTOtoEntity(userDto));
    }

    @Override
    public UserEntity updateUser(Long userId, UserDto userDto) {
        Optional<UserEntity> getUser= userService.getUser(userId);
        if(getUser.isPresent()){
            UserEntity userEntity= getUser.get();
            userEntity.setFirstName(userDto.getFirstName());
            userEntity.setLastName(userDto.getLastName());
            userEntity.setEmail(userDto.getEmail());
            return userService.updateUser(userId,getUser);
        }
        else{
            throw new RuntimeException("User Not Found");
        }

    }

    @Override
    public UserDto getUser(Long userId) {
        Optional<UserEntity> getUser= userService.getUser(userId);
        UserDto userDto = convertEntityToDTO(getUser.get());
        return userDto;
    }

    @Override
    public String deleteUser(Long userId) {
        return userService.deleteUser(userId);
    }

    public UserEntity convertDTOtoEntity(UserDto userDto){
        UserEntity userEntity=new UserEntity();
        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastName(userDto.getLastName());
        userEntity.setEmail(userDto.getEmail());
        return userEntity;
    }

    public UserDto convertEntityToDTO(UserEntity userEntity){
        UserDto userDto=new UserDto();
        userDto.setFirstName(userEntity.getFirstName());
        userDto.setLastName(userEntity.getLastName());
        userDto.setEmail(userEntity.getEmail());
        return userDto;
    }
}
