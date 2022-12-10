package ru.skillbox.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.skillbox.dto.AccountDto;
import ru.skillbox.dto.enums.NameNotification;
import ru.skillbox.model.Post;
import ru.skillbox.model.PostComment;
import ru.skillbox.response.settings.NotificationDataRs;
import ru.skillbox.response.settings.NotificationSentDto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class NotificationsService {

    @Autowired
    private final PersonService personService;

    public NotificationSentDto getNotifications(){
        NotificationSentDto notificationSentDto = new NotificationSentDto();
        NotificationDataRs data = new NotificationDataRs();
        AccountDto acc = AccountDto.getBase(personService.getCurrentPerson());
        data.setContent("что тут?");
        data.setId(acc.getId());
        data.setAuthor(acc);
        data.setSentTime(new Date().toString());
        data.setNotificationType(NameNotification.POST);
        notificationSentDto.getData().add(data);
        notificationSentDto.setTimeStamp(new Date().toString());
        return notificationSentDto;
    }


    public Integer notificationCount(){

        return 3;
    }

    public Object whatNotification(Object obj){
        NotificationSentDto notificationSentDto = new NotificationSentDto();
        List<NotificationDataRs> dataList = new ArrayList<>();
        notificationSentDto.setData(dataList);
        if (obj instanceof Post){

        }
        if (obj instanceof PostComment){

        }
        
        return obj;
    }


}
