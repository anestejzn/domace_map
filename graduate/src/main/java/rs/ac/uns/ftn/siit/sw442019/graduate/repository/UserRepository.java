package rs.ac.uns.ftn.siit.sw442019.graduate.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.siit.sw442019.graduate.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.email=?1 and u.verified=true")
    Optional<User> getVerifiedUser(String email);
    Optional<User> findByEmail(String email);

}
