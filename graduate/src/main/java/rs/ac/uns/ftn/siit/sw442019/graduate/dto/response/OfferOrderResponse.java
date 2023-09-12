package rs.ac.uns.ftn.siit.sw442019.graduate.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.siit.sw442019.graduate.model.OfferOrder;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OfferOrderResponse {
    private Long id;
    private OfferResponse offer;
    private double quantity;
    private double price;
    private boolean rated;

    public OfferOrderResponse(Long id, OfferResponse offer, double quantity, double price, boolean rated) {
        this.id = id;
        this.offer = offer;
        this.quantity = quantity;
        this.price = price;
        this.rated = rated;
    }

    public static List<OfferOrderResponse> formOfferOrderResponse(List<OfferOrder> offerOrders) {
        List<OfferOrderResponse> offerOrderResponses = new LinkedList<>();
        for(OfferOrder o : offerOrders){
            offerOrderResponses.add(new OfferOrderResponse
                    (o.getId(), new OfferResponse(o.getOffer()), o.getQuantity(), o.getPrice(), o.isRated()));
        }

        return offerOrderResponses;
    }
}
