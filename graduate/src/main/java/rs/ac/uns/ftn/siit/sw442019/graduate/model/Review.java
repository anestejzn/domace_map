package rs.ac.uns.ftn.siit.sw442019.graduate.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name="review")
@Setter
@Getter
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="text")
    private String text;

    @Column(name="rate")
    private double rate;

    @Column(name="date")
    private LocalDateTime date;

    @OneToOne()
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private RegularUser user;

    @ManyToOne
    @JoinColumn(name="offer_id", referencedColumnName = "id")
    private Offer offer;

    public Review(String text, double rate, RegularUser user, Offer offer, LocalDateTime date) {
        this.text = text;
        this.rate = rate;
        this.user = user;
        this.offer = offer;
        this.date = date;
    }
}
