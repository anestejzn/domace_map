package rs.ac.uns.ftn.siit.sw442019.graduate.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.siit.sw442019.graduate.enums.NotificationType;
import rs.ac.uns.ftn.siit.sw442019.graduate.model.Notification;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class NotificationResponse {

    private Long id;
    private Long orderId;
    private NotificationType type;
    private LocalDateTime dateTime;
    private boolean read;

    public NotificationResponse(Long id, Long orderId, NotificationType type, LocalDateTime dateTime, boolean read) {
        this.id = id;
        this.orderId = orderId;
        this.type = type;
        this.dateTime = dateTime;
        this.read = read;
    }

    public NotificationResponse(Long id, Long orderId, LocalDateTime dateTime) {
        this.id = id;
        this.orderId = orderId;
        this.dateTime = dateTime;
    }

    public NotificationResponse(Notification notification) {
        this.id = notification.getId();
        this.orderId = notification.getOrderId();
        this.type = notification.getType();
        this.dateTime = notification.getDateTime();
        this.read = notification.isRead();
    }
}
