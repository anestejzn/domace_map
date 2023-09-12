package rs.ac.uns.ftn.siit.sw442019.graduate.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ReviewPageResponse extends ReviewResponse {
    private int pageSize;
    private int pageNumber;

    public ReviewPageResponse(Long id, String userName, LocalDateTime date, double rate, String comment, OfferResponse offerResponse, String householdName, String householdCity, int pageSize, int pageNumber) {
        super(id, userName, date, rate, comment, offerResponse, householdName, householdCity);
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
    }
}
