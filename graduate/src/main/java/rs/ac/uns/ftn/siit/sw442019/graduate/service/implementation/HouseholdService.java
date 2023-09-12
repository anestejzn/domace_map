package rs.ac.uns.ftn.siit.sw442019.graduate.service.implementation;

import com.graphhopper.GHRequest;
import com.graphhopper.GHResponse;
import com.graphhopper.GraphHopper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.siit.sw442019.graduate.dto.response.*;
import rs.ac.uns.ftn.siit.sw442019.graduate.exception.EntityNotFoundException;
import rs.ac.uns.ftn.siit.sw442019.graduate.model.Address;
import rs.ac.uns.ftn.siit.sw442019.graduate.model.Household;
import rs.ac.uns.ftn.siit.sw442019.graduate.model.Offer;
import rs.ac.uns.ftn.siit.sw442019.graduate.model.RegularUser;
import rs.ac.uns.ftn.siit.sw442019.graduate.repository.HouseholdRepository;
import rs.ac.uns.ftn.siit.sw442019.graduate.service.interfaces.IAddressService;
import rs.ac.uns.ftn.siit.sw442019.graduate.service.interfaces.IHouseholdService;
import rs.ac.uns.ftn.siit.sw442019.graduate.service.interfaces.IRegularUserService;

import java.util.LinkedList;
import java.util.List;

import static rs.ac.uns.ftn.siit.sw442019.graduate.GraduateApplication.hopper;

@Service
public class HouseholdService implements IHouseholdService {
    @Autowired
    private HouseholdRepository householdRepository;
    @Autowired
    private IAddressService addressService;
    @Autowired
    private IRegularUserService regularUserService;

    public HouseholdResponse saveHousehold(String name, String registrationNumber, String phoneNumber, String city, String street, String number, double lon, double lat, Long userId) throws EntityNotFoundException {
        Address address = addressService.save(new Address(city, street, number, lon, lat));
        Household household = householdRepository.save(new Household(name, registrationNumber, phoneNumber, address));
        RegularUser user = regularUserService.getRegularUserById(userId);
        user.setHousehold(household);
        regularUserService.save(user);

        return new HouseholdResponse(household);
    }

    public Household getById(Long id){
        return householdRepository.getReferenceById(id);
    }

    public List<SearchResponse> getOffersForSearch(List<String> typesOfProduct, double lat, double lon){
        if(typesOfProduct == null) {
            if(lat > 0 && lon > 0) {
                List<Household> households = getAll();
                return searchForDistance(lat, lon, households);
            } else{
                return new LinkedList<>();
            }
        }else{
            if(lat > 0 && lon > 0) {

                return searchForTypeAndDistance(lat, lon, typesOfProduct);
            } else {

                return searchForType(typesOfProduct);
            }
        }
    }

    @Override
    public List<HouseholdPageResponse> getHouseholdsWithPagination(int pageNumber, int pageSize) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        Page<Household> households = householdRepository.findAll(page);

        return formHouseholdPageResponse(households.getContent(), households.getSize(), households.getTotalPages());
    }

    @Override
    public HouseholdResponse getHousehold(Long id) {

        return new HouseholdResponse(getById(id));
    }

    public List<Household> getAll(){
        return householdRepository.findAll();
    }

    private List<SearchResponse> searchForTypeAndDistance(double lat, double lon, List<String> types) {
        List<Household> households = searchHouseholdForType(types);

        return searchForDistance(lat, lon, households);
    }

    private List<SearchResponse> searchForType(List<String> types) {
        List<SearchResponse> searchedOffers = new LinkedList<>();
        List<Household> households = searchHouseholdForType(types);
        for(Household household : households) {
            searchedOffers.addAll(formSearchResponse(household.getAddress().getLat(), household.getAddress().getLon(), new HouseholdResponse(household), household.getOffers()));
        }

        return searchedOffers;
    }

    private List<Household> searchHouseholdForType(List<String> types) {
        List<Household> households = getAll();
        List<Household> searchedHouseholds = new LinkedList<>();
        for(Household household : households) {
            List<Offer> selectedOffers = new LinkedList<>();
            for(Offer offer : household.getOffers()) {
                if(types.contains(offer.getType())){
                    selectedOffers.add(offer);
                    household.setOffers(selectedOffers);
                    searchedHouseholds.add(household);
                }
            }
        }

        return searchedHouseholds;
    }

    private List<SearchResponse> searchForDistance(double lat, double lon, List<Household> households){
        List<SearchResponse> searchedOffers = new LinkedList<>();
        for(Household household : households){
            if(household.getOffers().size() > 0 && getDistance(lon, lat, household.getAddress().getLon(), household.getAddress().getLat()) / 1000 <= 50){
                searchedOffers.addAll(formSearchResponse(household.getAddress().getLat(), household.getAddress().getLon(), new HouseholdResponse(household), household.getOffers()));
            }
        }

        return searchedOffers;
    }

    private List<SearchResponse> formSearchResponse(double lat, double lon, HouseholdResponse householdResponse, List<Offer> offers){
        List<SearchResponse> searchResponses = new LinkedList<>();
        double lonUpdated = lon;
        for(Offer offer : offers) {
            lonUpdated = lonUpdated + 0.03;
            searchResponses.add(new SearchResponse(lonUpdated , lat, householdResponse, new OfferResponse(offer)));
        }

        return searchResponses;
    }

    private double getDistance(double lonStart, double latStart, double lonEnd, double latEnd){
        GHRequest request = new GHRequest(latStart, lonStart, latEnd, lonEnd);
        request.setProfile("car");
        GHResponse route = hopper.route(request);

        return route.getBest().getDistance();
    }

    private static List<HouseholdPageResponse> formHouseholdPageResponse(final List<Household> households, final int pageSize, final int pageNumber){
        List<HouseholdPageResponse> householdPageResponses = new LinkedList<>();
        for (Household household : households) {
            householdPageResponses.add(new HouseholdPageResponse(household, pageSize, pageNumber));
        }

        return householdPageResponses;
    }
}
