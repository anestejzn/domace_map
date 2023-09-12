package rs.ac.uns.ftn.siit.sw442019.graduate.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.siit.sw442019.graduate.dto.response.NotificationResponse;
import rs.ac.uns.ftn.siit.sw442019.graduate.enums.NotificationType;
import rs.ac.uns.ftn.siit.sw442019.graduate.model.Notification;
import rs.ac.uns.ftn.siit.sw442019.graduate.repository.NotificationRepository;
import rs.ac.uns.ftn.siit.sw442019.graduate.service.interfaces.INotificationService;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Service
public class NotificationService implements INotificationService {
    @Autowired
    private NotificationRepository notificationRepository;
    @Override
    public Notification save(Long orderId, NotificationType type, LocalDateTime dateTime, Long userId) {

        return notificationRepository.save(new Notification(orderId, type, dateTime, userId));
    }

    @Override
    public List<NotificationResponse> getAllForUser(Long userId) {

        return formNotificationResponses(notificationRepository.findAllByUserId(userId));
    }

    @Override
    public void readAllNotifications(Long userId) {
        List<Notification> unreadNotifications = notificationRepository.findAllByReadIsFalseAndUserId(userId);
        for(Notification notification : unreadNotifications) {
            notification.setRead(true);
            notificationRepository.save(notification);
        }
    }

    private List<NotificationResponse> formNotificationResponses(List<Notification> notifications){
        List<NotificationResponse> notificationResponses = new LinkedList<>();
        for (Notification notification : notifications) {
            notificationResponses.add(new NotificationResponse(notification));
        }

        return notificationResponses;
    }


}
