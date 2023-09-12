package rs.ac.uns.ftn.siit.sw442019.graduate.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.siit.sw442019.graduate.model.Offer;
import rs.ac.uns.ftn.siit.sw442019.graduate.model.RegularUser;

import java.util.LinkedList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class OfferPageResponse extends OfferResponse{
    private int pageSize;
    private int pageNumber;

    public OfferPageResponse(Offer offer, int pageSize, int pageNumber){
        super(offer);
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
    }

}
