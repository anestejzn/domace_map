package rs.ac.uns.ftn.siit.sw442019.graduate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.siit.sw442019.graduate.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
