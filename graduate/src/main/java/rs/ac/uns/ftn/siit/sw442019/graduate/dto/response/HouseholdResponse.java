package rs.ac.uns.ftn.siit.sw442019.graduate.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.siit.sw442019.graduate.model.Household;
import java.util.List;

import static rs.ac.uns.ftn.siit.sw442019.graduate.dto.response.OfferResponse.formOfferResponses;

@Getter
@Setter
@NoArgsConstructor
public class HouseholdResponse {
    private Long id;
    private String name;
    private String registrationNumber;
    private String phoneNumber;
    private AddressResponse address;
    private List<OfferResponse> offers;

    public HouseholdResponse(Long id, String name, String registrationNumber, String phoneNumber, AddressResponse address) {
        this.id = id;
        this.name = name;
        this.registrationNumber = registrationNumber;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public HouseholdResponse(Household household){
        this.id = household.getId();
        this.name = household.getName();
        this.registrationNumber = household.getRegistrationNumber();
        this.phoneNumber = household.getPhoneNumber();
        this.address = new AddressResponse(household.getAddress());
        this.offers = formOfferResponses(household.getOffers());
    }
}
