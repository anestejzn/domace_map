package rs.ac.uns.ftn.siit.sw442019.graduate.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Entity
@Table(name="product_order")
@Setter
@Getter
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName="id")
    private RegularUser user;

    @ManyToOne
    @JoinColumn(name="household_id", referencedColumnName="id")
    private Household household;

    @OneToOne
    @JoinColumn(name="address_id", referencedColumnName="id")
    private Address address;

    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name="date_time")
    private LocalDateTime dateTime;

    @Column(name="sentAt")
    private LocalDateTime sentAt = null;

    @Column(name="deliveredAt")
    private LocalDateTime deliveredAt = null;

    @Column(name="cancelled")
    private boolean cancelled = false;

    @Column(name="cancelReason")
    private String cancelReason;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
    private List<OfferOrder> offers = new LinkedList<>();


    public Order(RegularUser user, Address address, String phoneNumber, Household household, List<OfferOrder> offers, LocalDateTime dateTime) {
        this.user = user;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.household = household;
        this.offers = offers;
        this.dateTime = dateTime;
    }
}
