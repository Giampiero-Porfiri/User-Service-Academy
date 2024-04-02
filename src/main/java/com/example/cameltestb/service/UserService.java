package com.example.cameltestb.service;

import com.example.cameltestb.dto.UserDto;
import com.example.cameltestb.entity.UserEntity;
import com.example.cameltestb.entity.mapper.UserEntityMapper;
import com.example.cameltestb.repository.UserRepository;
import com.example.cameltestb.util.AppEnum;
import com.example.cameltestb.util.GlobalResources;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.ProducerTemplate;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class UserService {

    @Autowired
    private ProducerTemplate producerTemplate;

    @Autowired
    private GlobalResources globalResources;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserEntityMapper userEntityMapper;

    @Value("${spring.mail.username}")
    private String from;

    public void register(UserDto userDto) {
        UserEntity userEntity = userEntityMapper.toUserEntity(userDto);

        Map<String, Object> headers = new HashMap<>();
        headers.put("username", userDto.getUsername());

        Boolean userExists = producerTemplate.requestBodyAndHeaders(
                "direct:verifyUsername", null, headers, Boolean.class);
        if (Boolean.TRUE.equals(userExists)) {
            log.error("Username " + userDto.getUsername() + " è già usato.");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username già in uso.");
        }

        userEntity = userRepository.saveAndFlush(userEntity);
        gianniCheck(userEntity.getName());

        log.info("Utente " + userEntity.getName() + " " + userEntity.getSurname() + " registrato. " +
                "Procedo a inviare email a: " + userEntity.getEmail());
        sendEmail(userEntity.getEmail());
    }

    public void deleteUsers() {
        userRepository.deleteAll();
    }

    private void sendEmail(String recipient) {
        Map<String, Object> headers = new HashMap<>();
        headers.put("To", recipient);
        headers.put("From", from);
        headers.put("Subject", AppEnum.REGISTRAZIONE.getNome());
        producerTemplate.sendBodyAndHeaders(
                "direct:sendEmail", "Congratulescions ti sei registrato", headers);
    }

    private void gianniCheck(String name) {
        if(name.equals("Gianni")) {
            globalResources.incrementGianniCounter();
        }
    }
}
