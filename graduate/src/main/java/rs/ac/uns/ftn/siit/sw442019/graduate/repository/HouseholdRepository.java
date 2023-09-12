package rs.ac.uns.ftn.siit.sw442019.graduate.repository;

import jakarta.persistence.Entity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.siit.sw442019.graduate.model.Household;

@Repository
public interface HouseholdRepository extends JpaRepository<Household, Long> {
    @Override
    Page<Household> findAll(Pageable pageable);
}
