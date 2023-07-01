package com.project.capstone.dto;

import com.project.capstone.model.UserRole;
import com.project.capstone.validation.FieldMatch;
import com.project.capstone.validation.ValidPassword;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;


@Getter
@Setter
@NoArgsConstructor
@FieldMatch.List({@FieldMatch(first = "password", second = "matchPassword", message = "The password fields must match")})
public class UserDTO {


    private String email;
    private String username;

    @ValidPassword
    private String password;
    private String matchPassword;
}
