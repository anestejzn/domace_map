package rs.ac.uns.ftn.siit.sw442019.graduate.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.siit.sw442019.graduate.dto.request.ReviewRequest;
import rs.ac.uns.ftn.siit.sw442019.graduate.dto.response.HouseholdPageResponse;
import rs.ac.uns.ftn.siit.sw442019.graduate.dto.response.ReviewPageResponse;
import rs.ac.uns.ftn.siit.sw442019.graduate.dto.response.ReviewResponse;
import rs.ac.uns.ftn.siit.sw442019.graduate.exception.EntityNotFoundException;
import rs.ac.uns.ftn.siit.sw442019.graduate.service.interfaces.IReviewService;

import java.util.List;

import static rs.ac.uns.ftn.siit.sw442019.graduate.util.Constants.*;

@RestController
@RequestMapping("review")
public class ReviewController {

    @Autowired
    private IReviewService reviewService;

    @PostMapping()
    @PreAuthorize("hasAuthority('SAVE_REVIEW')")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveReview(@RequestBody @Valid ReviewRequest request) throws EntityNotFoundException {
        reviewService.saveReview(request.getText(), request.getRate(), request.getOfferId(), request.getUserId(), request.getOfferOrderId());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('GET_REVIEWS')")
    @ResponseStatus(HttpStatus.OK)
    public List<ReviewResponse> getReviews(@PathVariable Long id) {

        return reviewService.getReviewsForOffer(id);
    }

    @DeleteMapping("/{id}/{offerId}")
    @PreAuthorize("hasAuthority('DELETE_REVIEW')")
    @ResponseStatus(HttpStatus.OK)
    public void deleteReview(@PathVariable Long id, @PathVariable Long offerId) throws EntityNotFoundException {

       reviewService.deleteReview(id, offerId);
    }

    @GetMapping("/{pageNumber}/{pageSize}")
    @PreAuthorize("hasAuthority('GET_REVIEWS')")
    @ResponseStatus(HttpStatus.OK)
    public List<ReviewPageResponse> getReviewsWithPagination(@Valid @NotNull(message = NOT_NULL_MESSAGE) @PositiveOrZero(message = POSITIVE_OR_ZERO_MESSAGE) @PathVariable int pageNumber,
                                                             @Valid @NotNull(message = NOT_NULL_MESSAGE) @Positive(message = POSITIVE_MESSAGE) @PathVariable int pageSize){
        return reviewService.getReviewsWithPagination(pageNumber, pageSize);
    }
}
