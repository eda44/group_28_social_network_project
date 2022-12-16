package ru.skillbox.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.skillbox.dto.AccountDto;
import ru.skillbox.dto.enums.NameNotification;
import ru.skillbox.model.Person;
import ru.skillbox.model.Post;
import ru.skillbox.model.PostComment;
import ru.skillbox.request.settings.NotificationInputDto;
import ru.skillbox.response.settings.*;
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
        List<NotificationDataRs> list = whatNotification();
        notificationSentDto.setData(list);
        notificationSentDto.setTimeStamp(new Date().toString());
        return notificationSentDto;
    }

    public NotificationCountRs notificationCount(){
        NotificationCountRs notificationCountRs = new NotificationCountRs();
        CountRs countRs = new CountRs();
        countRs.setCount(getNotifications().getData().size());
        notificationCountRs.setCountRs(countRs);
        notificationCountRs.setTimestamp(new Date().getTime());
        return notificationCountRs;
    }

    public List<NotificationDataRs> whatNotification(){
        Person person = personService.getCurrentPerson();
        List<NotificationDataRs> dataList = new ArrayList<>();

        if (person.getSettingsNotification().isPost()) {
            for (Post post : person.getPostList()) {
                dataList.add(save(post.getId(),
                        post.getTitle(),
                        post.getTime().toString(),
                        NameNotification.POST,
                        AccountDto.getBase(person)));
            }
        }
        if (person.getSettingsNotification().isPostComment()) {
            for (PostComment postComment : person.getPostCommentList()){
                dataList.add(save(postComment.getId(),
                        postComment.getCommentText(),
                        postComment.getTime().toString(),
                        NameNotification.POST_COMMENT,
                        AccountDto.getBase(person)));
            }
        }

        return dataList;
    }

    public NotificationDto getNotification(NotificationInputDto notificationInputDto){

        return NotificationDto
                .builder()
                .id(notificationInputDto.getUserId())
                .notificationType(notificationInputDto.getNameNotification())
                .authorId(notificationInputDto.getAuthorId())
                .content(notificationInputDto.getContent())
                .time(new Date().toString())
                .userId(notificationInputDto.getUserId())
                .isStatusSent(false)
                .build();
    }

    public NotificationDataRs save(Long id,
                                   String title,
                                   String time,
                                   NameNotification name,
                                   AccountDto acc){
        NotificationDataRs notificationDataRs = new NotificationDataRs();
        notificationDataRs.setNotificationType(name);
        notificationDataRs.setSentTime(time);
        notificationDataRs.setContent(title);
        notificationDataRs.setAuthor(acc);
        notificationDataRs.setId(id);
        return notificationDataRs;
    }
}
