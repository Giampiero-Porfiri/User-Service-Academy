package com.example.cameltestb.batchprocessing;

import com.example.cameltestb.dto.UserDto;
import com.example.cameltestb.repository.UserRepository;
import com.example.cameltestb.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserServiceItemWriter implements ItemWriter<UserDto> {
    private final UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    public UserServiceItemWriter(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void write(Chunk<? extends UserDto> chunk) {
        for (UserDto userDto : chunk) {
            userService.register(userDto);
        }
    }
}
