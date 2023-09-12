package rs.ac.uns.ftn.siit.sw442019.graduate.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SearchRequest {
    private double lon;
    private double lat;
    private List<String> productType;

    public SearchRequest(double lon, double lat) {
        this.lon = lon;
        this.lat = lat;
    }

    public SearchRequest(List<String> productType){
        this.productType = productType;
    }
}
