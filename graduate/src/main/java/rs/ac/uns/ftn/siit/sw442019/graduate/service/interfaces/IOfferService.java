package rs.ac.uns.ftn.siit.sw442019.graduate.service.interfaces;

import rs.ac.uns.ftn.siit.sw442019.graduate.dto.response.OfferPageResponse;
import rs.ac.uns.ftn.siit.sw442019.graduate.exception.EntityNotFoundException;
import rs.ac.uns.ftn.siit.sw442019.graduate.exception.EntityUpdateException;
import rs.ac.uns.ftn.siit.sw442019.graduate.model.Offer;

import java.util.List;

public interface IOfferService {

    void saveNewOffer(String name, String description, double price, String colForPrice, List<String> photos, Long householdId, String type) throws EntityUpdateException;

    List<OfferPageResponse> getOffersWithPagination(Long householdId, int pageNumber, int pageSize);

    void updateOffer(String name, String description, double price, String colForPrice, List<String> photos, Long id) throws EntityUpdateException;

    void delete(Long id);

    Offer getById(Long id) throws EntityNotFoundException;

    void calculateRate(long id) throws EntityNotFoundException;
}
