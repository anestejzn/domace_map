package rs.ac.uns.ftn.siit.sw442019.graduate.dto.response;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.siit.sw442019.graduate.model.Offer;

import java.util.LinkedList;
import java.util.List;

import static rs.ac.uns.ftn.siit.sw442019.graduate.util.PictureHandler.convertPictureToBase64ByName;

@NoArgsConstructor
@Getter
@Setter
public class OfferResponse {
    private Long id;
    private String name;
    private String description;
    private double averageRate;
    private double price;
    private String colForPrice;
    private List<String> photos = new LinkedList<>();
    private String type;

    public OfferResponse(Offer offer){
        this.id = offer.getId();
        this.name = offer.getName();
        this.description = offer.getDescription();
        this.averageRate = offer.getAverageRate();
        this.price = offer.getPrice();
        this.colForPrice = offer.getColForPrice();
        List<String> convertedPhotos = new LinkedList<>();
        for(String photo : offer.getPhotos()){
            convertedPhotos.add(convertPictureToBase64ByName(photo));
        }
        this.photos = convertedPhotos;
        this.type = offer.getType();
    }

    public OfferResponse(Long id, String name, String description, double averageRate, double price, String colForPrice, List<String> photos) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.averageRate = averageRate;
        this.price = price;
        this.colForPrice = colForPrice;
        this.photos = photos;
    }

    public static List<OfferResponse> formOfferResponses(List<Offer> offers) {
        List<OfferResponse> offerResponses = new LinkedList<>();
        for(Offer offer : offers) {
            offerResponses.add(new OfferResponse(offer));
        }

        return offerResponses;
    }
}
