package rs.ac.uns.ftn.siit.sw442019.graduate.service.interfaces;

import rs.ac.uns.ftn.siit.sw442019.graduate.dto.response.HouseholdPageResponse;
import rs.ac.uns.ftn.siit.sw442019.graduate.dto.response.HouseholdResponse;
import rs.ac.uns.ftn.siit.sw442019.graduate.dto.response.OfferPageResponse;
import rs.ac.uns.ftn.siit.sw442019.graduate.dto.response.SearchResponse;
import rs.ac.uns.ftn.siit.sw442019.graduate.exception.EntityNotFoundException;
import rs.ac.uns.ftn.siit.sw442019.graduate.model.Household;

import java.util.List;

public interface IHouseholdService {
    HouseholdResponse saveHousehold(String name, String registrationNumber, String phoneNumber, String city, String street, String number, double lon, double lat, Long userId) throws EntityNotFoundException;
    Household getById(Long id);
    List<SearchResponse> getOffersForSearch(List<String> typeOfProduct, double lat, double lon);
    List<HouseholdPageResponse> getHouseholdsWithPagination(int pageNumber, int pageSize);

    HouseholdResponse getHousehold(Long id);
}
