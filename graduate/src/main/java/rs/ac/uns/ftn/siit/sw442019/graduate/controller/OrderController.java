package rs.ac.uns.ftn.siit.sw442019.graduate.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.siit.sw442019.graduate.dto.request.OrderRequest;
import rs.ac.uns.ftn.siit.sw442019.graduate.dto.request.RejectOrderRequest;
import rs.ac.uns.ftn.siit.sw442019.graduate.dto.response.OrderResponse;
import rs.ac.uns.ftn.siit.sw442019.graduate.exception.EntityNotFoundException;
import rs.ac.uns.ftn.siit.sw442019.graduate.exception.MailCannotBeSentException;
import rs.ac.uns.ftn.siit.sw442019.graduate.service.interfaces.IOrderService;

import java.util.List;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE_ORDER')")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveOrder(@Valid @RequestBody OrderRequest orderRequest) throws EntityNotFoundException, MailCannotBeSentException {
        orderService.saveOrder(orderRequest.getUserId(), orderRequest.getItems(), orderRequest.getAddress().getStreet(), orderRequest.getAddress().getNumber(), orderRequest.getAddress().getCity(), orderRequest.getPhoneNumber());
    }

    @GetMapping("/{forHousehold}/{id}")
    @PreAuthorize("hasAuthority('GET_ORDERS')")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderResponse> getOrders(@PathVariable boolean forHousehold, @PathVariable  Long id) {

            return orderService.getOrders(forHousehold, id);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('GET_ORDER')")
    @ResponseStatus(HttpStatus.OK)
    public OrderResponse getOrder(@PathVariable Long id) throws EntityNotFoundException {

        return orderService.getOrder(id);
    }

    @GetMapping("/sent/{id}")
    @PreAuthorize("hasAuthority('SENT_ORDER')")
    @ResponseStatus(HttpStatus.OK)
    public OrderResponse markAsSentOrder(@PathVariable Long id) throws MailCannotBeSentException, EntityNotFoundException {

        return orderService.markAsSentOrder(id);
    }

    @PostMapping("/reject")
    @PreAuthorize("hasAuthority('REJECT_ORDER')")
    @ResponseStatus(HttpStatus.OK)
    public OrderResponse markAsRejectedOrder(@RequestBody RejectOrderRequest rejectOrderRequest) throws MailCannotBeSentException, EntityNotFoundException {

        return orderService.markAsRejectedOrder(rejectOrderRequest.getId(), rejectOrderRequest.getReason());
    }

    @GetMapping("/delivered/{id}")
    @PreAuthorize("hasAuthority('DELIVERED_ORDER')")
    @ResponseStatus(HttpStatus.OK)
    public OrderResponse markAsDeliveredOrder(@PathVariable Long id) throws EntityNotFoundException {

        return orderService.markAsDeliveredOrder(id);
    }


}
