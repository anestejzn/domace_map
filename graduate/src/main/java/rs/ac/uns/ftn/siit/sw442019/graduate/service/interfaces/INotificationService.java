package rs.ac.uns.ftn.siit.sw442019.graduate.service.interfaces;

import rs.ac.uns.ftn.siit.sw442019.graduate.dto.response.NotificationResponse;
import rs.ac.uns.ftn.siit.sw442019.graduate.enums.NotificationType;
import rs.ac.uns.ftn.siit.sw442019.graduate.model.Notification;

import java.time.LocalDateTime;
import java.util.List;

public interface INotificationService {

    Notification save(Long orderId, NotificationType type, LocalDateTime dateTime, Long userId);

    List<NotificationResponse> getAllForUser(Long userId);

    void readAllNotifications(Long userId);
}
