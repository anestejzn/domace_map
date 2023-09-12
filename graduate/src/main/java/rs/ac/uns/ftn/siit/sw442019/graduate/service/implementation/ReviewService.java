package rs.ac.uns.ftn.siit.sw442019.graduate.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.siit.sw442019.graduate.dto.response.HouseholdPageResponse;
import rs.ac.uns.ftn.siit.sw442019.graduate.dto.response.OfferResponse;
import rs.ac.uns.ftn.siit.sw442019.graduate.dto.response.ReviewPageResponse;
import rs.ac.uns.ftn.siit.sw442019.graduate.dto.response.ReviewResponse;
import rs.ac.uns.ftn.siit.sw442019.graduate.exception.EntityNotFoundException;
import rs.ac.uns.ftn.siit.sw442019.graduate.model.*;
import rs.ac.uns.ftn.siit.sw442019.graduate.repository.ReviewRepository;
import rs.ac.uns.ftn.siit.sw442019.graduate.service.interfaces.IReviewService;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Service
public class ReviewService implements IReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private RegularUserService regularUserService;
    @Autowired
    private OfferService offerService;
    @Autowired
    private OfferOrderService offerOrderService;

    @Override
    public void saveReview(String text, double rate, Long offerId, Long userId, Long offerOrderId) throws EntityNotFoundException {
        RegularUser regularUser = regularUserService.getRegularUserById(userId);
        Offer offer = offerService.getById(offerId);
        reviewRepository.save(new Review(text, rate, regularUser, offer, LocalDateTime.now()));
        OfferOrder offerOrder = offerOrderService.getById(offerOrderId);
        offerOrder.setRated(true);
        offerOrderService.save(offerOrder);
        offerService.calculateRate(offerId);
    }

    @Override
    public List<ReviewResponse> getReviewsForOffer(Long id) {
        List<ReviewResponse> reviewResponses = new LinkedList<>();
        List<Review> foundReviews = reviewRepository.findByOfferId(id);
        for(Review review : foundReviews) {
            reviewResponses.add(new ReviewResponse(
                    review.getId(),
                    String.format("%s %s", review.getUser().getName(), review.getUser().getSurname()),
                    review.getDate(),
                    review.getRate(),
                    review.getText(),
                    new OfferResponse(review.getOffer()),
                    review.getOffer().getHousehold().getName(),
                    review.getOffer().getHousehold().getAddress().getCity()
            ));
        }

        return reviewResponses;
    }

    @Override
    public void deleteReview(Long id, Long offerId) throws EntityNotFoundException {
        reviewRepository.deleteById(id);
        offerService.calculateRate(offerId);
    }

    @Override
    public List<ReviewPageResponse> getReviewsWithPagination(int pageNumber, int pageSize) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        Page<Review> reviews = reviewRepository.findAll(page);

        return formReviewPageResponse(reviews.getContent(), reviews.getSize(), reviews.getTotalPages());
    }

    private static List<ReviewPageResponse> formReviewPageResponse(final List<Review> reviews, final int pageSize, final int pageNumber){
        List<ReviewPageResponse> reviewPageResponses = new LinkedList<>();
        for (Review review : reviews) {
            reviewPageResponses.add(new ReviewPageResponse(
                    review.getId(),
                    String.format("%s %s", review.getUser().getName(), review.getUser().getSurname()),
                    review.getDate(),
                    review.getRate(),
                    review.getText(),
                    new OfferResponse(review.getOffer()),
                    review.getOffer().getHousehold().getName(),
                    review.getOffer().getHousehold().getAddress().getCity(),
                    pageSize, pageNumber)
            );
        }

        return reviewPageResponses;
    }
}
