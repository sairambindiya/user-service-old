package com.user.userservice.service;

import com.github.javafaker.Faker;
import com.user.userservice.entity.UserEntity;
import com.user.userservice.repository.UserRepository;
import org.slf4j.LoggerFactory;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserService {
    Faker faker=new Faker();

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    private UserRepository userRepository;

    /*public void createMillionUsers(UserDto userDto){
        logger.info("Service start time {}", LocalDateTime.now());
        for(int i=0;i<1000000;i++){
            UserEntity user=new UserEntity();
            user.setFirstName(faker.name().firstName());
            user.setLastName(faker.name().lastName());
            user.setEmail(faker.internet().emailAddress());
            userRepository.save(user);
        }
        logger.info("Service end time {}", LocalDateTime.now());

    }*/

    public UserEntity createUser(UserEntity userEntity){
        UserEntity user=new UserEntity();
        user.setFirstName(userEntity.getFirstName());
        user.setLastName(userEntity.getLastName());
        user.setEmail(userEntity.getEmail());
        user.setUserId(userEntity.getUserId());
       return userRepository.save(userEntity);
    }

    public long countUsers() {
        return userRepository.count();
    }

    public Optional<UserEntity> getUser(Long userId) {
        return Optional.ofNullable(userRepository.findByUserId(userId).orElseThrow());
    }

    public String deleteUser(Long userId) {
        userRepository.deleteById(userId);
        return "User record "+userId +" deleted";
    }

    public UserEntity updateUser(Long userId, Optional<UserEntity> userEntity) {
        Optional<UserEntity> userEntity1= getUser(userId);
        if(userEntity1.isPresent())
        {
            UserEntity extractUserEntity=userEntity1.get();
            extractUserEntity.setFirstName(userEntity.get().getFirstName());
            extractUserEntity.setLastName(userEntity.get().getLastName());
            extractUserEntity.setUserId(userEntity.get().getUserId());
            return userRepository.save(extractUserEntity);
        }
    else{
     throw new RuntimeException("User Not found");
        }

    }
}
