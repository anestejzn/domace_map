package rs.ac.uns.ftn.siit.sw442019.graduate.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.siit.sw442019.graduate.dto.request.OfferRequest;
import rs.ac.uns.ftn.siit.sw442019.graduate.dto.response.OfferPageResponse;
import rs.ac.uns.ftn.siit.sw442019.graduate.exception.EntityUpdateException;
import rs.ac.uns.ftn.siit.sw442019.graduate.service.interfaces.IOfferService;

import java.util.List;

import static rs.ac.uns.ftn.siit.sw442019.graduate.util.Constants.*;

@RestController
@RequestMapping("offer")
public class OfferController {

    @Autowired
    private IOfferService offerService;

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE_OFFER')")
    @ResponseStatus(HttpStatus.CREATED)
    public void createOffer(@RequestBody OfferRequest offerRequest) throws EntityUpdateException {
        offerService.saveNewOffer(
                offerRequest.getName(),
                offerRequest.getDescription(),
                offerRequest.getPrice(),
                offerRequest.getColForPrice(),
                offerRequest.getPhotos(),
                offerRequest.getHouseholdId(),
                offerRequest.getType()
        );
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('UPDATE_OFFER')")
    @ResponseStatus(HttpStatus.OK)
    public void updateOffer(@RequestBody OfferRequest offerRequest, @PathVariable Long id) throws EntityUpdateException {
        offerService.updateOffer(
                offerRequest.getName(),
                offerRequest.getDescription(),
                offerRequest.getPrice(),
                offerRequest.getColForPrice(),
                offerRequest.getPhotos(),
                id
        );
    }

    @GetMapping("/{householdId}/{pageNumber}/{pageSize}")
    @PreAuthorize("hasAuthority('GET_OFFERS')")
    @ResponseStatus(HttpStatus.OK)
    public List<OfferPageResponse> getOffersWithPagination(@Valid @NotNull(message = NOT_NULL_MESSAGE) @PositiveOrZero(message = POSITIVE_OR_ZERO_MESSAGE) @PathVariable int pageNumber,
                                                           @Valid @NotNull(message = NOT_NULL_MESSAGE) @PathVariable Long householdId,
                                                           @Valid @NotNull(message = NOT_NULL_MESSAGE) @Positive(message = POSITIVE_MESSAGE) @PathVariable int pageSize){
        return offerService.getOffersWithPagination(householdId, pageNumber, pageSize);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE_OFFER')")
    @ResponseStatus(HttpStatus.OK)
    public void deleteOffer(@Valid @NotNull(message = NOT_NULL_MESSAGE) @PathVariable Long id){
        offerService.delete(id);
    }





}
