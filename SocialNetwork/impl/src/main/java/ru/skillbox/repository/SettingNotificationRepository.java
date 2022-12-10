package ru.skillbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skillbox.model.SettingsNotification;

@Repository
public interface SettingNotificationRepository extends JpaRepository<SettingsNotification, Long> {
}
