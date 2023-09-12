package rs.ac.uns.ftn.siit.sw442019.graduate.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name="offer_order")
@Setter
@Getter
@NoArgsConstructor
public class OfferOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "offer_id", referencedColumnName = "id")
    private Offer offer;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    @Column(name="quantity")
    private double quantity;

    @Column(name="price")
    private double price;

    @Column(name="rated")
    private boolean rated = false;


    public OfferOrder(Offer offer, double quantity, double price) {
        this.offer = offer;
        this.quantity = quantity;
        this.price = price;
    }
}
