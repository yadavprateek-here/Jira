package com.prakar.jira.util;

import com.prakar.jira.dto.CreateTicket;
import com.prakar.jira.dto.UserRegistration;
import com.prakar.jira.entity.Ticket;
import com.prakar.jira.entity.User;
import com.prakar.jira.entity.UserInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

//@Mapper(
//        componentModel = "spring",
//        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
//)
//public interface DataMapper {
//
//    void updateUserFromDto(UserRegistration dto,@MappingTarget User user);
//    void updateUserToDto( User user ,@MappingTarget UserRegistration dto);
//}

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DataMapper {

    // create
    User userRegistrationToUser(UserRegistration dto);
    UserInfo userRegistrationToUserInfo(UserRegistration dto);

    // response
    @Mapping(target = "password", ignore = true)
    UserRegistration toDto(UserInfo userInfo);

    Ticket dtoToTicket (CreateTicket t);


}