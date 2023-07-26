package com.project.artistwebsite.dto;

import com.project.artistwebsite.validation.FieldMatch;
import com.project.artistwebsite.validation.ValidPassword;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


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
