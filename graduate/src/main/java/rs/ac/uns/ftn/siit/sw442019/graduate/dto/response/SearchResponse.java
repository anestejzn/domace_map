package rs.ac.uns.ftn.siit.sw442019.graduate.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.siit.sw442019.graduate.model.Offer;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchResponse {

    private double lon;
    private double lat;
    private HouseholdResponse household;
    private OfferResponse offer;
}
