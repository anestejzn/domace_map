package rs.ac.uns.ftn.siit.sw442019.graduate.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.siit.sw442019.graduate.dto.response.NotificationResponse;
import rs.ac.uns.ftn.siit.sw442019.graduate.enums.NotificationType;
import rs.ac.uns.ftn.siit.sw442019.graduate.model.Notification;
import java.time.LocalDateTime;

@Service
public class WebSocketService {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private NotificationService notificationService;

    public void sendNewOrderNotification(
            final Long orderId,
            final String email,
            final Long userId
    ) {
        sendNotification(orderId, email, NotificationType.NEW_ORDER, "/new-order", userId);
    }

    public void sendRejectedOrderNotification(
            final Long orderId,
            final String email,
            final Long userId
    ) {
        sendNotification(orderId, email, NotificationType.REJECTED_ORDER, "/rejected-order", userId);
    }

    public void sendSentOrderNotification(
            final Long orderId,
            final String email,
            final Long userId
    ) {
        sendNotification(orderId, email, NotificationType.SENT_ORDER, "/sent-order", userId);
    }

    private void sendNotification(Long orderId, String email, NotificationType type, String destination, Long userId) {

        Notification notification = notificationService.save(orderId, type, LocalDateTime.now(), userId);
        NotificationResponse notificationResponse = new NotificationResponse(notification);
        this.messagingTemplate.convertAndSendToUser(email, destination, notificationResponse);
    }

}
