package com.example.cameltestb.dto.mapper;

import com.example.cameltestb.batchprocessing.record.UserRecord;
import com.example.cameltestb.controller.request.CreateUserRequest;
import com.example.cameltestb.dto.UserDto;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        builder = @Builder(disableBuilder = true),
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserDtoMapper {
    UserDto toUserDto(CreateUserRequest createUserRequest);

    UserDto userRecordToUserDto(UserRecord userRecord);
}
