package rs.ac.uns.ftn.siit.sw442019.graduate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.siit.sw442019.graduate.dto.response.NotificationResponse;
import rs.ac.uns.ftn.siit.sw442019.graduate.service.implementation.NotificationService;
import rs.ac.uns.ftn.siit.sw442019.graduate.service.interfaces.INotificationService;

import java.util.List;

@RestController
@RequestMapping("notification")
public class NotificationController {

    @Autowired
    private INotificationService notificationService;

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<NotificationResponse> getAllNotificationsForUser(@PathVariable Long userId) {

        return notificationService.getAllForUser(userId);
    }

    @GetMapping("/read/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void readAllNotificationsForUser(@PathVariable Long userId) {

        notificationService.readAllNotifications(userId);
    }
}
