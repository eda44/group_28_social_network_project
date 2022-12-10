package ru.skillbox.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.skillbox.dto.AccountDto;
import ru.skillbox.dto.CityDto;
import ru.skillbox.dto.CountryDto;
import ru.skillbox.model.*;
import ru.skillbox.response.settings.NotificationDataRs;
import ru.skillbox.response.settings.NotificationSentDto;
import ru.skillbox.request.settings.SettingRq;
import ru.skillbox.response.settings.NotificationSettingsDto;
import ru.skillbox.response.settings.SettingsDto;
import ru.skillbox.dto.enums.NameNotification;
import ru.skillbox.repository.SettingNotificationRepository;
import java.util.Date;

@Service
@AllArgsConstructor
public class SettingService {

    @Autowired
    private PersonService personService;

    @Autowired
    private SettingNotificationRepository settingNotificationRepository;


    public NotificationSettingsDto getSetting(){
        SettingsNotification settings = getSettingNotif();
        NotificationSettingsDto settingsDto = new NotificationSettingsDto();
        settingsDto.setTime(new Date().toString());
        settingsDto.getListSettings().add(SettingRq.getSettingRq(
                NameNotification.FRIEND_REQUEST, settings.isFriendRequest()));
        settingsDto.getListSettings().add(SettingRq.getSettingRq(
                NameNotification.FRIEND_BIRTHDAY, settings.isFriendBirthday()));
        settingsDto.getListSettings().add(SettingRq.getSettingRq(
                NameNotification.POST_COMMENT, settings.isPostComment()));
        settingsDto.getListSettings().add(SettingRq.getSettingRq(
                NameNotification.COMMENT_COMMENT, settings.isCommentComment()));
        settingsDto.getListSettings().add(SettingRq.getSettingRq(
                NameNotification.POST, settings.isPost()));
        settingsDto.getListSettings().add(SettingRq.getSettingRq(
                NameNotification.MESSAGE, settings.isMessage()));
        settingsDto.getListSettings().add(SettingRq.getSettingRq(
                NameNotification.SEND_EMAIL_MESSAGE, settings.isSendEmailMessage()));
        settingsDto.setUserId(settings.getId());
        return settingsDto;
    }


    public void saveSettings(NameNotification notification, boolean bool){
        SettingsNotification setting = getSettingNotif();
        if (notification.getName().equals("friendRequest")){
            setting.setFriendRequest(bool);
        }
        if (notification.getName().equals("postComment")){
            setting.setPostComment(bool);
        }
        if (notification.getName().equals("friendBirthday")){
            setting.setFriendBirthday(bool);
        }
        if (notification.getName().equals("post")){
            setting.setPost(bool);
        }
        if (notification.getName().equals("commentComment")){
            setting.setCommentComment(bool);
        }
        if (notification.getName().equals("message")){
            setting.setMessage(bool);
        }
        if (notification.getName().equals("sendPhoneMessage")){
            setting.setSendPhoneMessage(bool);
        }
        if (notification.getName().equals("sendEmailMessage")){
            setting.setSendEmailMessage(bool);
        }
        settingNotificationRepository.save(setting);
    }


    public SettingsDto compareSettings(SettingsDto settingsDto){
        SettingsNotification settingNotification = getSettingNotif();
        if (settingNotification.getId().equals(settingsDto.getId())) {
            settingNotification.setPost(settingsDto.isPost());
            settingNotification.setMessage(settingsDto.isMessage());
            settingNotification.setCommentComment(settingsDto.isCommentComment());
            settingNotification.setPostComment(settingsDto.isPostComment());
            settingNotification.setFriendBirthday(settingsDto.isFriendBirthday());
            settingNotification.setFriendRequest(settingsDto.isFriendRequest());
            settingNotification.setSendEmailMessage(settingsDto.isSendEmailMessage());
            settingNotification.setSendPhoneMessage(settingsDto.isSendPhoneMessage());
            settingNotificationRepository.save(settingNotification);
        }
        return settingsDto;
    }

    public SettingsNotification getSettingNotif(){
        SettingsNotification settingsNotification = settingNotificationRepository
                .getById(personService
                        .getCurrentPerson()
                        .getSettingsNotification().getId());
        return settingsNotification;
    }
}