package rs.ac.uns.ftn.siit.sw442019.graduate.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.siit.sw442019.graduate.model.Review;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponse {
    private Long id;
    private String userName;
    private LocalDateTime date;
    private double rate;
    private String text;
    private OfferResponse offer;
    private String householdName;
    private String householdCity;

}
