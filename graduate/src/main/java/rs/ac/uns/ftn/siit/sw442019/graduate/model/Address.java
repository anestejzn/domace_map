package rs.ac.uns.ftn.siit.sw442019.graduate.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="address")
@Setter
@Getter
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="city")
    private String city;

    @Column(name="street")
    private String street;

    @Column(name="number")
    private String number;

    @Column(name="lon", nullable = false)
    private double lon;

    @Column(name="lat", nullable = false)
    private double lat;


    public Address(String city, String street, String number, double lon, double lat) {
        this.city = city;
        this.street = street;
        this.number = number;
        this.lon = lon;
        this.lat = lat;
    }
    public Address(String city, String street, String number) {
        this.city = city;
        this.street = street;
        this.number = number;
    }
}
