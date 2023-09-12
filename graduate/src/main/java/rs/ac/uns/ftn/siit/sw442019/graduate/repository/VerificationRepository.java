package rs.ac.uns.ftn.siit.sw442019.graduate.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.siit.sw442019.graduate.model.RegistrationVerification;

import java.util.Optional;

@Repository
public interface VerificationRepository extends JpaRepository<RegistrationVerification, Long> {

    Optional<RegistrationVerification> getRegistrationVerificationsById(Long id);
    Optional<RegistrationVerification> getRegistrationVerificationsByHashedId(String id);

}
