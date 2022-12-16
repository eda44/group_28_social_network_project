package ru.skillbox.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.skillbox.dto.AccountDto;
import ru.skillbox.model.Dialog;
import ru.skillbox.model.Message;
import ru.skillbox.model.Person;
import ru.skillbox.response.data.DialogDto;
import ru.skillbox.response.data.MessageDto;

@Mapper(componentModel = "spring")
public interface DialogMapper {

    @Mapping(target = "conversationPartner", qualifiedByName = "account")
    DialogDto DialogToDto(Dialog dialog);

    @Mapping(target = "authorId", qualifiedByName = "personToAccount")
    @Mapping(target = "recipientId", qualifiedByName = "personToAccount")
    MessageDto MessageToDto(Message message);

    @Mapping(target = "authorId", qualifiedByName = "idToPerson")
    @Mapping(target = "recipientId", qualifiedByName = "idToPerson")
    Message DtoToMessage(MessageDto messageDto);

    @Named("personToAccount")
    default Long personToAccount(Person person) {
        return person.getId();
    }


    @Named("idToPerson")
    default Person idToPerson(Long id) {
        Person person = new Person();
        person.setId(id);
        return person;
    }

    @Named("account")
    default AccountDto account(Person person){
        return AccountMapper.INSTANCE.personToAccountDto(person);
    }
}
