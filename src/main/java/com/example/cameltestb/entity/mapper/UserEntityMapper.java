package com.example.cameltestb.entity.mapper;

import com.example.cameltestb.dto.UserDto;
import com.example.cameltestb.entity.UserEntity;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        builder = @Builder(disableBuilder = true),
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserEntityMapper {
    UserEntity toUserEntity(UserDto userDto);
}
