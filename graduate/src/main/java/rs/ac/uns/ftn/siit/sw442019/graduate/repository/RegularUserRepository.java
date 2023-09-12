package rs.ac.uns.ftn.siit.sw442019.graduate.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.siit.sw442019.graduate.model.RegularUser;

import java.util.Optional;

@Repository
public interface RegularUserRepository extends JpaRepository<RegularUser, Long> {

    Optional<RegularUser> getRegularUserByEmail(String email);
    Optional<RegularUser> getRegularUserById(Long id);

}
