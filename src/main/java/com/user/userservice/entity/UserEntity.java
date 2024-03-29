package com.user.userservice.entity;

import com.user.userservice.dto.UserDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.id.factory.internal.AutoGenerationTypeStrategy;

@Getter
@Setter
@Entity
@Table(name="users")
public class UserEntity {

    private String firstName;
    private String lastName;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long userId;
    private String email;

    public static UserDto convertToDto(UserEntity user) {
            UserDto userDTO = new UserDto();
            userDTO.setFirstName(user.getFirstName());
            userDTO.setLastName(user.getLastName());
            userDTO.setUserId(user.getUserId());
            userDTO.setEmail(user.getEmail());
            return userDTO;
    }
}
