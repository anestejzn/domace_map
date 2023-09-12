package rs.ac.uns.ftn.siit.sw442019.graduate.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import rs.ac.uns.ftn.siit.sw442019.graduate.model.Offer;

public interface OfferRepository extends JpaRepository<Offer, Long> {

    @Query(value = "select * from offer o where o.household_id=?1", nativeQuery = true)
    Page<Offer> findByHouseholdId(final Long id, final Pageable pageable);
}
