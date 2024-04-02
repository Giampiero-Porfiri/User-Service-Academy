package com.example.cameltestb.batchprocessing;

import com.example.cameltestb.batchprocessing.record.UserRecord;
import com.example.cameltestb.dto.UserDto;
import com.example.cameltestb.dto.mapper.UserDtoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserItemProcessor implements ItemProcessor<UserRecord, UserDto> {
    @Autowired
    private UserDtoMapper userDtoMapper;

    @Override
    public UserDto process(final UserRecord userRecord) {
        log.info("Convertendo il record utente csv in userDto.");
        return userDtoMapper.userRecordToUserDto(userRecord);
    }
}
