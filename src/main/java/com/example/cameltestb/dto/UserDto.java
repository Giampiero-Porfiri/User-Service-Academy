package com.example.cameltestb.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserDto {
    private String name;
    private String surname;
    private String username;
    private String password;
    private String email;
}
