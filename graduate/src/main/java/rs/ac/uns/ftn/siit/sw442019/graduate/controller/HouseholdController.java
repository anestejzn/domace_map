package rs.ac.uns.ftn.siit.sw442019.graduate.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.siit.sw442019.graduate.dto.request.HouseholdRequest;
import rs.ac.uns.ftn.siit.sw442019.graduate.dto.request.SearchRequest;
import rs.ac.uns.ftn.siit.sw442019.graduate.dto.response.HouseholdPageResponse;
import rs.ac.uns.ftn.siit.sw442019.graduate.dto.response.HouseholdResponse;
import rs.ac.uns.ftn.siit.sw442019.graduate.dto.response.OfferPageResponse;
import rs.ac.uns.ftn.siit.sw442019.graduate.dto.response.SearchResponse;
import rs.ac.uns.ftn.siit.sw442019.graduate.exception.EntityNotFoundException;
import rs.ac.uns.ftn.siit.sw442019.graduate.service.interfaces.IHouseholdService;

import java.util.List;

import static rs.ac.uns.ftn.siit.sw442019.graduate.util.Constants.*;

@RestController
@RequestMapping("household")
public class HouseholdController {

    @Autowired
    private IHouseholdService householdService;

    @PostMapping
    @PreAuthorize("hasAuthority('SAVE_HOUSEHOLD')")
    @ResponseStatus(HttpStatus.CREATED)
    public HouseholdResponse saveHousehold(@Valid @RequestBody HouseholdRequest request) throws EntityNotFoundException {
        return householdService.saveHousehold(request.getName(), request.getRegistrationNumber(), request.getPhoneNumber(),
                                       request.getAddress().getCity(), request.getAddress().getStreet(), request.getAddress().getNumber(),
                                       request.getAddress().getLon(), request.getAddress().getLat(), request.getUserId()
                                       );
    }

    @PostMapping("search")
    @ResponseStatus(HttpStatus.OK)
    public List<SearchResponse> getOffersForSearch(@RequestBody SearchRequest request) {
        return householdService.getOffersForSearch(request.getProductType(), request.getLat(), request.getLon());
    }

    @GetMapping("/{pageNumber}/{pageSize}")
    @PreAuthorize("hasAuthority('GET_HOUSEHOLDS')")
    @ResponseStatus(HttpStatus.OK)
    public List<HouseholdPageResponse> getHouseholdsWithPagination(@Valid @NotNull(message = NOT_NULL_MESSAGE) @PositiveOrZero(message = POSITIVE_OR_ZERO_MESSAGE) @PathVariable int pageNumber,
                                                                   @Valid @NotNull(message = NOT_NULL_MESSAGE) @Positive(message = POSITIVE_MESSAGE) @PathVariable int pageSize){
        return householdService.getHouseholdsWithPagination(pageNumber, pageSize);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('GET_HOUSEHOLD')")
    @ResponseStatus(HttpStatus.OK)
    public HouseholdResponse getHousehold(@Valid @NotNull(message = NOT_NULL_MESSAGE) @PathVariable Long id){
        return householdService.getHousehold(id);
    }
}
