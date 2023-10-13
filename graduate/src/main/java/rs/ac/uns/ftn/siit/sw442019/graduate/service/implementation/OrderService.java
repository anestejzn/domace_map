package rs.ac.uns.ftn.siit.sw442019.graduate.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.siit.sw442019.graduate.dto.ItemEmail;
import rs.ac.uns.ftn.siit.sw442019.graduate.dto.OrderEmail;
import rs.ac.uns.ftn.siit.sw442019.graduate.dto.request.OrderItem;
import rs.ac.uns.ftn.siit.sw442019.graduate.dto.response.OrderResponse;
import rs.ac.uns.ftn.siit.sw442019.graduate.enums.EntityType;
import rs.ac.uns.ftn.siit.sw442019.graduate.exception.EntityNotFoundException;
import rs.ac.uns.ftn.siit.sw442019.graduate.exception.MailCannotBeSentException;
import rs.ac.uns.ftn.siit.sw442019.graduate.model.*;
import rs.ac.uns.ftn.siit.sw442019.graduate.repository.OrderRepository;
import rs.ac.uns.ftn.siit.sw442019.graduate.service.interfaces.IOrderService;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static rs.ac.uns.ftn.siit.sw442019.graduate.util.TypeHelper.getImageForType;

@Service
public class OrderService implements IOrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private RegularUserService regularUserService;
    @Autowired
    private OfferOrderService offerOrderService;
    @Autowired
    private OfferService offerService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private WebSocketService webSocketService;
    @Override
    public void saveOrder(Long userId, List<OrderItem> items, String street, String number, String city, String phoneNumber) throws EntityNotFoundException, MailCannotBeSentException {
        RegularUser user = regularUserService.getRegularUserById(userId);
        Address address = addressService.save(new Address(city, street, number));
        Map<Long, OrderEmail> dict = new HashMap<>();
        Map<Household, List<OfferOrder>> householdOfferOrders = new HashMap<>();
        for(OrderItem item : items) {
            Offer offer = offerService.getById(item.getOfferId());
            OfferOrder offerOrder = offerOrderService.save(new OfferOrder(offer, item.getQuantity(), item.getPrice()));
            prepareForSaving(householdOfferOrders, offer, offerOrder);
            prepareForSendingEmail(street, number, city, phoneNumber, user, dict, item, offer, offerOrder);
        }

        saveOrdersAndSendEmails(phoneNumber, user, address, dict, householdOfferOrders);
    }

    private void saveOrdersAndSendEmails(String phoneNumber, RegularUser user, Address address, Map<Long, OrderEmail> dict, Map<Household, List<OfferOrder>> householdOfferOrders) throws MailCannotBeSentException {
        for (Map.Entry<Household,List<OfferOrder>> entry : householdOfferOrders.entrySet()) {
            Order order = orderRepository.save(new Order(user, address, phoneNumber, entry.getKey(), entry.getValue(), LocalDateTime.now()));
            for(OfferOrder offerOrder : entry.getValue()) {
                offerOrder.setOrder(order);
                offerOrderService.save(offerOrder);
            }
            sendEmailsAndNotifications(dict, order.getId());
        }
    }

    private static void prepareForSendingEmail(String street, String number, String city, String phoneNumber, RegularUser user, Map<Long, OrderEmail> dict, OrderItem item, Offer offer, OfferOrder offerOrder) {
        if(dict.containsKey(offer.getHousehold().getId())){
            dict.get(offer.getHousehold().getId()).getItems().add(new ItemEmail(offer.getName(), "", String.format("%d x %s", item.getQuantity(), offer.getColForPrice()), item.getPrice()));
        }
        else {
            List<ItemEmail> newList = new LinkedList<>();
            newList.add(new ItemEmail(offer.getName(), getImageForType(offer.getType()), String.format("%d x %s", item.getQuantity(), offer.getColForPrice()), item.getPrice()));

            dict.put(offer.getHousehold().getId(), new OrderEmail(
                    String.format("%s %s", user.getName(), user.getSurname()),
                    String.format("%s %s", street, number),
                            city, phoneNumber, String.format("000%d", offerOrder.getId()),
                            newList, 0.00)
                    );
        }
    }

    private static void prepareForSaving(Map<Household, List<OfferOrder>> householdOfferOrders, Offer offer, OfferOrder offerOrder) {
        if(householdOfferOrders.containsKey(offer.getHousehold())){
            householdOfferOrders.get(offer.getHousehold()).add(offerOrder);
        } else {
            List<OfferOrder> offerOrderList = new LinkedList<>();
            offerOrderList.add(offerOrder);
            householdOfferOrders.put(offer.getHousehold(), offerOrderList);
        }
    }

    @Override
    public List<OrderResponse> getOrders(boolean forHousehold, Long id) {
        List<Order> orders = forHousehold ? orderRepository.findByHouseholdId(id) :
                            orderRepository.findByUserId(id);

        return formOrderResponseList(orders);
    }

    @Override
    public OrderResponse getOrder(Long id) throws EntityNotFoundException {

        return new OrderResponse(getOrderById(id));
    }

    @Override
    public OrderResponse markAsDeliveredOrder(Long id) throws EntityNotFoundException {
        Order order = getOrderById(id);
        order.setDeliveredAt(LocalDateTime.now());

        return new OrderResponse(orderRepository.save(order));
    }

    @Override
    public OrderResponse markAsSentOrder(Long id) throws MailCannotBeSentException, EntityNotFoundException {
        Order order = getOrderById(id);
        order.setSentAt(LocalDateTime.now());
        Order savedOrder = orderRepository.save(order);
        sendEmailAboutOrder(savedOrder, "POSLATA PORUDZBINA", "Obavestavamo Vas da je Vasa porudzbina poslata.");
        webSocketService.sendSentOrderNotification(order.getId(), order.getUser().getEmail(), order.getUser().getId());
        return new OrderResponse(savedOrder);
    }

    @Override
    public OrderResponse markAsRejectedOrder(Long id, String reason) throws MailCannotBeSentException, EntityNotFoundException {
        Order order = getOrderById(id);
        order.setCancelled(true);
        order.setCancelReason(reason);
        Order savedOrder = orderRepository.save(order);
        sendEmailAboutOrder(savedOrder, "ODBIJENA PORUDZBINA", String.format("Obavestavamo Vas da je Vasa porudzbina odbijena. Navedeni razlog: %s", reason));
        webSocketService.sendRejectedOrderNotification(order.getId(), order.getUser().getEmail(), order.getUser().getId());
        return new OrderResponse(savedOrder);
    }



    private Order getOrderById(Long id) throws EntityNotFoundException {

        return orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, EntityType.OFFER));
    }

    private List<OrderResponse> formOrderResponseList(List<Order> orders) {
        List<OrderResponse> orderResponses = new LinkedList<>();
        for(Order order : orders) {
            orderResponses.add(new OrderResponse(
                    order
            ));
        }

        return orderResponses;
    }

    private void sendEmailAboutOrder(Order order, String title, String message) throws MailCannotBeSentException {
        List<ItemEmail> items = new LinkedList<>();
        double totalPrice = 0;
        for(OfferOrder offerOrder : order.getOffers()){
            totalPrice += offerOrder.getPrice();
            items.add(new ItemEmail(
                    offerOrder.getOffer().getName(),
                    getImageForType(offerOrder.getOffer().getType()),
                    String.valueOf((int)offerOrder.getQuantity()),
                    (int) offerOrder.getPrice()
                    ));
        }
        OrderEmail orderEmail = new OrderEmail(
                String.format("%s %s", order.getUser().getName(), order.getUser().getSurname()),
                String.format("%s %s", order.getAddress().getStreet(), order.getAddress().getNumber()),
                order.getAddress().getCity(), order.getPhoneNumber(),
                String.format("000%d", order.getId()), items, (int) totalPrice
        );

        emailService.sendOrderMail(title, message, orderEmail, order.getUser().getEmail());
    }


    private void sendEmailsAndNotifications(Map<Long, OrderEmail> dict, Long orderId) throws MailCannotBeSentException {
        List<RegularUser> regularUsers = regularUserService.getAll();
        for(RegularUser regularUser : regularUsers){
            if(regularUser.getHousehold() != null){
                if(dict.containsKey(regularUser.getHousehold().getId())){
                    OrderEmail orderEmail = dict.get(regularUser.getHousehold().getId());
                    orderEmail.setTotalPrice(calculatePrice(orderEmail.getItems()));
                    emailService.sendOrderMail("NOVA PORUDZBINA", "Imate novu porudzbinu. Molimo Vas, obavestite nas o statusu porudzbine.", orderEmail, regularUser.getEmail());
                    webSocketService.sendNewOrderNotification(orderId, regularUser.getEmail(), regularUser.getId());
                }
            }
        }
    }

    private double calculatePrice(List<ItemEmail> items) {
        double price = 0;
        for (ItemEmail itemEmail : items) {
            price += itemEmail.getPrice();
        }

        return price;
    }
}
