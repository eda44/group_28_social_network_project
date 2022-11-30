package ru.skillbox.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import ru.skillbox.dto.DialogDto;
import ru.skillbox.dto.MessageDto;
import ru.skillbox.model.Dialog;
import ru.skillbox.model.Message;
import ru.skillbox.model.Person;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DialogMapper {

    DialogDto toDto(Dialog dialog);

    @Mapping(target = "authorId", qualifiedByName = "personToAccount")
    @Mapping(target = "recipientId", qualifiedByName = "personToAccount")
    MessageDto toDto(Message message);

    @Named("personToAccount")
    default Long personToAccount(Person person) {
        return person.getId();
    }
}
