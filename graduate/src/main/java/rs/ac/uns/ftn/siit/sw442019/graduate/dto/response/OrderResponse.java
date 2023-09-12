package rs.ac.uns.ftn.siit.sw442019.graduate.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.siit.sw442019.graduate.model.*;

import java.time.LocalDateTime;
import java.util.List;

import static rs.ac.uns.ftn.siit.sw442019.graduate.dto.response.OfferOrderResponse.formOfferOrderResponse;

@NoArgsConstructor
@Getter
@Setter
public class OrderResponse {
    private Long id;
    private LocalDateTime dateTime;
    private LocalDateTime sentAt;
    private LocalDateTime deliveredAt;
    private boolean cancelled;
    private String cancelReason;
    private RegularUser user;
    private HouseholdResponse household;
    private AddressResponse addressOrder;
    private String phoneNumber;
    private List<OfferOrderResponse> offerOrders;

    public OrderResponse(Order order) {
        this.id = order.getId();
        this.dateTime = order.getDateTime();
        this.sentAt = order.getSentAt();
        this.deliveredAt = order.getDeliveredAt();
        this.cancelled = order.isCancelled();
        this.cancelReason = order.getCancelReason();
        this.user = order.getUser();
        this.household = new HouseholdResponse(order.getHousehold());
        this.addressOrder = new AddressResponse(order.getAddress());
        this.phoneNumber = order.getPhoneNumber();
        this.offerOrders = formOfferOrderResponse(order.getOffers());
    }

}
