package rs.ac.uns.ftn.siit.sw442019.graduate.service.interfaces;

import rs.ac.uns.ftn.siit.sw442019.graduate.dto.response.ReviewPageResponse;
import rs.ac.uns.ftn.siit.sw442019.graduate.dto.response.ReviewResponse;
import rs.ac.uns.ftn.siit.sw442019.graduate.exception.EntityNotFoundException;

import java.util.List;

public interface IReviewService {

    void saveReview(String text, double rate, Long offerId, Long userId, Long offerOrderId) throws EntityNotFoundException;
    List<ReviewResponse> getReviewsForOffer(Long id);

    void deleteReview(Long id, Long offerId) throws EntityNotFoundException;

    List<ReviewPageResponse> getReviewsWithPagination(int pageNumber, int pageSize);
}
