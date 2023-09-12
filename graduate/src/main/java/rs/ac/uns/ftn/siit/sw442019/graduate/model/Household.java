package rs.ac.uns.ftn.siit.sw442019.graduate.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name="household")
@Setter
@Getter
@NoArgsConstructor
public class Household {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="name")
    private String name;
    @Column(name="registration_number")
    private String registrationNumber;
    @Column(name="phone_number")
    private String phoneNumber;
    @OneToOne()
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "household")
    private List<Offer> offers = new LinkedList<>();
    @OneToMany(fetch = FetchType.LAZY)
    private List<Order> orders = new LinkedList<>();


    public Household(String name, String registrationNumber, String phoneNumber, Address address) {
        this.name = name;
        this.registrationNumber = registrationNumber;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
}
