package rs.ac.uns.ftn.siit.sw442019.graduate.service.interfaces;

import rs.ac.uns.ftn.siit.sw442019.graduate.dto.request.OrderItem;
import rs.ac.uns.ftn.siit.sw442019.graduate.dto.response.OrderResponse;
import rs.ac.uns.ftn.siit.sw442019.graduate.exception.EntityNotFoundException;
import rs.ac.uns.ftn.siit.sw442019.graduate.exception.MailCannotBeSentException;

import java.util.List;

public interface IOrderService {

    void saveOrder(Long userId, List<OrderItem> items, String street, String number, String city, String phoneNumber) throws EntityNotFoundException, MailCannotBeSentException;
    List<OrderResponse> getOrders(boolean forHousehold, Long userId);
    OrderResponse getOrder(Long id) throws EntityNotFoundException;
    OrderResponse markAsDeliveredOrder(Long id) throws EntityNotFoundException;
    OrderResponse markAsSentOrder(Long id) throws MailCannotBeSentException, EntityNotFoundException;
    OrderResponse markAsRejectedOrder(Long id, String reason) throws MailCannotBeSentException, EntityNotFoundException;
}
