package com.user.userservice.controller;

import com.user.userservice.entity.UserEntity;
import com.user.userservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserEntity createUser(@RequestBody UserEntity userEntity) {
        return userService.createUser(userEntity);
    }

    @PutMapping("/{userId}")
    public UserEntity updateUser(@PathVariable Long userId,@RequestBody UserEntity userEntity){
        return userService.updateUser(userId,userEntity);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserEntity> getUser(@PathVariable Long userId){
        return userService.getUser(userId);
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable Long userId){
        return userService.deleteUser(userId);
    }

}


