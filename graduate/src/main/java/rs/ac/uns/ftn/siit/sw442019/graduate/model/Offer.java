package rs.ac.uns.ftn.siit.sw442019.graduate.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="offer")
@Setter
@Getter
@NoArgsConstructor
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="name")
    private String name;
    @Column(name="description")
    private String description;
    @Column(name="average_rate")
    private double averageRate;
    @Column(name="price")
    private double price;
    @Column(name="col_for_price")
    private String colForPrice;
    @Column(name="type")
    private String type;
    @ManyToOne
    @JoinColumn(name = "household_id", referencedColumnName = "id")
    private Household household;
    @ElementCollection
    @CollectionTable(name = "offer_photo", joinColumns = @JoinColumn(name = "offer_id"))
    @Column(name = "photo")
    private List<String> photos = new LinkedList<>();
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "offer")
    private List<Review> reviews = new LinkedList<>();

    public Offer(String name, String description, double price, String colForPrice, Household household, String type) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.colForPrice = colForPrice;
        this.household = household;
        this.type = type;
    }
}
