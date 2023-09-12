package rs.ac.uns.ftn.siit.sw442019.graduate.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.siit.sw442019.graduate.model.Address;

@Getter
@Setter
@NoArgsConstructor
public class AddressResponse {
    private String street;
    private String number;
    private String city;
    private double lon;
    private double lat;


    public AddressResponse(String street, String number, String city, double lon, double lat) {
        this.street = street;
        this.number = number;
        this.city = city;
        this.lon = lon;
        this.lat = lat;
    }

    public AddressResponse(Address address){
        this.street = address.getStreet();
        this.number = address.getNumber();
        this.city = address.getCity();
        this.lat = address.getLat();
        this.lon = address.getLon();
    }
}
