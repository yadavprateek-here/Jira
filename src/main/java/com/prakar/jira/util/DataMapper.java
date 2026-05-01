package com.prakar.jira.util;

import com.prakar.jira.dto.UserRegistration;
import com.prakar.jira.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface DataMapper {

    User updateUserFromDto(UserRegistration dto);
    void updateUserToDto( User user ,@MappingTarget UserRegistration dto);
}