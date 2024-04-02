package com.example.cameltestb.controller;

import com.example.cameltestb.controller.request.CreateUserRequest;
import com.example.cameltestb.dto.UserDto;
import com.example.cameltestb.dto.mapper.UserDtoMapper;
import com.example.cameltestb.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Slf4j
@Validated
@CrossOrigin(origins = "*", methods = RequestMethod.POST, allowedHeaders = "*")
public class UserController {

    private final UserService userService;
    private final UserDtoMapper userDtoMapper;

    @PostMapping("/register")
    public void createUser(@Valid @RequestBody CreateUserRequest createUserRequest) {
        UserDto userDto = userDtoMapper.toUserDto(createUserRequest);

        log.info("Sto registrando: " + userDto.getName() + " " + userDto.getSurname() + ".");
        userService.register(userDto);

        log.info("Registrazione effettuata con successo.");
    }

    @DeleteMapping("/delete")
    public void deleteUsers() {
        log.warn("Sto cancellando tutti gli utenti");

        userService.deleteUsers();

        log.warn("Tutti gli utenti sono stati cancellati");
    }
}
