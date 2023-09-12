package rs.ac.uns.ftn.siit.sw442019.graduate.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.siit.sw442019.graduate.enums.NotificationType;

import java.time.LocalDateTime;

@Entity
@Table(name="notification")
@Setter
@Getter
@NoArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="order_id")
    private Long orderId;
    @Column(name="type")
    private NotificationType type;
    @Column(name="date_time")
    private LocalDateTime dateTime;
    @Column(name="read")
    private boolean read = false;
    @Column(name = "user_id", nullable = false)
    private Long userId;

    public Notification(Long orderId, NotificationType type, LocalDateTime dateTime, Long userId) {
        this.orderId = orderId;
        this.type = type;
        this.dateTime = dateTime;
        this.userId = userId;
    }
}
