package ru.skillbox.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FriendshipMapper {

//    @Mapping(target = "id", source = "id")
//    @Mapping(target = "firstName", source = "firstName")
//    @Mapping(target = "lastName", source = "lastName")
//    @Mapping(target = "birthDate", source = "birthDate", qualifiedByName = "convertDateToLong")
//    @Mapping(target = "email", source = "email")
//    @Mapping(target = "phone", source = "phone")
//    @Mapping(target = "photo", source = "photo")
//    @Mapping(target = "city", source = "city")
//    @Mapping(target = "country", source = "country")
//    @Mapping(target = "lastOnlineTime", source = "lastOnlineTime", qualifiedByName = "convertDateToLong")
//    @Mapping(target = "isBlocked", source = "isBlocked")
//    @Mapping(target = "friendshipStatus", source = "code")
//
//    FriendshipResponseDto personToFriendship(Person person, FriendshipCodeDto code);
}
