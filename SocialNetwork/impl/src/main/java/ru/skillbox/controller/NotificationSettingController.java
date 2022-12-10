package ru.skillbox.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skillbox.request.settings.NotificationInputDto;
import ru.skillbox.request.settings.SettingRq;
import ru.skillbox.response.settings.NotificationSentDto;
import ru.skillbox.response.settings.DefaultRs;
import ru.skillbox.response.settings.NotificationSettingsDto;
import ru.skillbox.response.settings.SettingsDto;
import ru.skillbox.service.NotificationsService;
import ru.skillbox.service.SettingService;

import java.util.Date;

@RestController
@RequestMapping("/api/v1")
public class NotificationSettingController {

    private final SettingService settingService;

    private final NotificationsService notificationsService;

    private static final Logger logger = LogManager
            .getLogger(NotificationSettingController.class);

    @Autowired
    public NotificationSettingController(SettingService settingService,
                                         NotificationsService notificationsService){
        this.settingService = settingService;
        this.notificationsService = notificationsService;
    }


    @GetMapping("/notifications/settings")
    public ResponseEntity<NotificationSettingsDto> getSettings(){
        return ResponseEntity.ok(settingService.getSetting());
    }

    @PutMapping("/notifications/settings")
    public ResponseEntity<DefaultRs> putSettings(@RequestBody SettingRq settingRq){
        settingService.saveSettings(settingRq.getNotificationType(), settingRq.isEnable());
        DefaultRs defaultRs = new DefaultRs();
        defaultRs.setTime((new Date().toString()));
        defaultRs.setStatus(true);
        logger.info("putSetting" + settingRq
                .getNotificationType()
                .getName() +
                " : " + settingRq.isEnable());
        return ResponseEntity.ok().body(defaultRs);
    }

    @PostMapping("/notifications/settings")
    public SettingsDto createSettings(SettingsDto settingsDto){
        logger.info("postSetting" + settingService
                .compareSettings(settingsDto)
                .toString());
        return settingService.compareSettings(settingsDto);
    }

    @GetMapping("/notifications")
    public ResponseEntity<NotificationSentDto> getNotification() {
        logger.info("getNotification " + notificationsService
                .getNotifications()
                .getTimeStamp());
        logger.info("проверка " + settingService.getSetting().getUserId());
        return ResponseEntity.ok(notificationsService.getNotifications());
    }

    @PostMapping("/notifications")
    public ResponseEntity<?> createNotification(@RequestBody NotificationInputDto notif){
        return ResponseEntity.ok("{\n" +
                "  \"userId\": 4,\n" +
                "  \"nameNotification\": \"FRIEND_REQUEST\",\n" +
                "  \"content\": \"что-то пришло\"\n" +
                "}");
    }

    @GetMapping("/notifications/count")
    public ResponseEntity<Object> getNotificationCount(){
        return ResponseEntity.ok("{\n" +
                "  \"timestamp\": 1,\n" +
                "  \"data\": {\n" +
                "    \"count\": 1\n" +
                "  }\n" +
                "}");
    }
}