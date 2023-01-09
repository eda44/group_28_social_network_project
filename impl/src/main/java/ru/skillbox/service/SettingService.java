package ru.skillbox.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.skillbox.model.*;
import ru.skillbox.request.settings.SettingRq;
import ru.skillbox.response.settings.NotificationSettingsDto;
import ru.skillbox.response.settings.SettingsDto;
import ru.skillbox.dto.enums.NameNotification;
import ru.skillbox.repository.SettingRepository;
import java.util.Date;

@Service
@AllArgsConstructor
public class SettingService {

    @Autowired
    private PersonService personService;

    @Autowired
    private SettingRepository settingRepository;


    public NotificationSettingsDto getSetting(){
        Settings settings = getSettingNotif();
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
        Settings setting = getSettingNotif();
        if (notification.getName().equals("friendRequest")){
            setting.setFriendRequest(bool);
        }
        else if (notification.getName().equals("postComment")){
            setting.setPostComment(bool);
        }
        else if (notification.getName().equals("friendBirthday")){
            setting.setFriendBirthday(bool);
        }
        else if (notification.getName().equals("post")){
            setting.setPost(bool);
        }
        else if (notification.getName().equals("commentComment")){
            setting.setCommentComment(bool);
        }
        else if (notification.getName().equals("message")){
            setting.setMessage(bool);
        }
        else if (notification.getName().equals("sendPhoneMessage")){
            setting.setSendPhoneMessage(bool);
        }
        else if (notification.getName().equals("sendEmailMessage")){
            setting.setSendEmailMessage(bool);
        }
        settingRepository.save(setting);
    }


    public SettingsDto compareSettings(SettingsDto settingsDto){
        Settings settingNotification = getSettingNotif();
        if (settingNotification.getId().equals(settingsDto.getId())) {
            settingNotification.setPost(settingsDto.isPost());
            settingNotification.setMessage(settingsDto.isMessage());
            settingNotification.setCommentComment(settingsDto.isCommentComment());
            settingNotification.setPostComment(settingsDto.isPostComment());
            settingNotification.setFriendBirthday(settingsDto.isFriendBirthday());
            settingNotification.setFriendRequest(settingsDto.isFriendRequest());
            settingNotification.setSendEmailMessage(settingsDto.isSendEmailMessage());
            settingNotification.setSendPhoneMessage(settingsDto.isSendPhoneMessage());
            settingRepository.save(settingNotification);
        }
        return settingsDto;
    }

    public Settings getSettingNotif(){
        Settings settings = settingRepository.findById(personService
                        .getCurrentPerson()
                        .getSettings().getId()).get();
        return settings;
    }
}