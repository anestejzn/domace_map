package rs.ac.uns.ftn.siit.sw442019.graduate.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.siit.sw442019.graduate.dto.response.OfferPageResponse;
import rs.ac.uns.ftn.siit.sw442019.graduate.enums.EntityType;
import rs.ac.uns.ftn.siit.sw442019.graduate.exception.EntityNotFoundException;
import rs.ac.uns.ftn.siit.sw442019.graduate.exception.EntityUpdateException;
import rs.ac.uns.ftn.siit.sw442019.graduate.model.Household;
import rs.ac.uns.ftn.siit.sw442019.graduate.model.Offer;
import rs.ac.uns.ftn.siit.sw442019.graduate.model.Review;
import rs.ac.uns.ftn.siit.sw442019.graduate.repository.OfferRepository;
import rs.ac.uns.ftn.siit.sw442019.graduate.service.interfaces.IHouseholdService;
import rs.ac.uns.ftn.siit.sw442019.graduate.service.interfaces.IOfferService;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import static rs.ac.uns.ftn.siit.sw442019.graduate.util.PictureHandler.generateSavePhotoPath;
import static rs.ac.uns.ftn.siit.sw442019.graduate.util.PictureHandler.savePicture;

@Service
public class OfferService implements IOfferService {

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private IHouseholdService householdService;

    @Override
    public void saveNewOffer(String name, String description, double price, String colForPrice, List<String> photos, Long householdId, String type) throws EntityUpdateException {
        Household household = householdService.getById(householdId);
        Offer offer = new Offer(name, description, price, colForPrice,household, type);
        Offer savedOffer = offerRepository.save(offer);
        if(photos.size() > 0) {
            List<String> photosForSave = new LinkedList<>();
            for (int i = 0; i < photos.size(); i++) {
                String newPhotoPath = savePicture(photos.get(i), savedOffer.getId(), i);
                photosForSave.add(newPhotoPath);
            }
            offer.setPhotos(photosForSave);
            offerRepository.save(offer);
        }
    }

    @Override
    public List<OfferPageResponse> getOffersWithPagination(Long householdId, int pageNumber, int pageSize) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        Page<Offer> offers = offerRepository.findByHouseholdId(householdId, page);

        return formOfferPageResponse(offers.getContent(), offers.getSize(), offers.getTotalPages());
    }

    @Override
    public void updateOffer(String name, String description, double price, String colForPrice, List<String> photos, Long id) throws EntityUpdateException {
        Offer offer = offerRepository.getReferenceById(id);
        offer.setName(name);
        offer.setDescription(description);
        offer.setPrice(price);
        offer.setColForPrice(colForPrice);
        List<String> photosForSave = new LinkedList<>();
        for(String photo : offer.getPhotos()){
            File file = new File(generateSavePhotoPath(photo));
            file.delete();
        }
        for (int i = 0; i < photos.size(); i++) {
            String newPhotoPath = savePicture(photos.get(i), offer.getId(), i);
            photosForSave.add(newPhotoPath);
        }
        offer.setPhotos(photosForSave);
        offerRepository.save(offer);
    }

    @Override
    public void delete(Long id) {
        Offer offer = offerRepository.getReferenceById(id);
        for(String photo : offer.getPhotos()){
            File file = new File(generateSavePhotoPath(photo));
            file.delete();
        }
        offerRepository.delete(offer);
    }

    @Override
    public Offer getById(Long id) throws EntityNotFoundException {
        return offerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, EntityType.OFFER));
    }

    @Override
    public void calculateRate(long id) throws EntityNotFoundException {
        Offer offer = getById(id);
        double rate = 0;
        for(Review review : offer.getReviews()) {
            rate += review.getRate();
        }

        offer.setAverageRate(rate / offer.getReviews().size());
        offerRepository.save(offer);
    }

    private static List<OfferPageResponse> formOfferPageResponse(final List<Offer> offers, final int pageSize, final int pageNumber){
        List<OfferPageResponse> offerPageResponses = new LinkedList<>();
        for (Offer offer : offers) {
            OfferPageResponse offerPageResponse = new OfferPageResponse(offer, pageSize, pageNumber);
            offerPageResponses.add(offerPageResponse);
        }

        return offerPageResponses;
    }

}
