package rs.ac.uns.ftn.siit.sw442019.graduate.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.siit.sw442019.graduate.model.Address;
import rs.ac.uns.ftn.siit.sw442019.graduate.repository.AddressRepository;
import rs.ac.uns.ftn.siit.sw442019.graduate.service.interfaces.IAddressService;

@Service
public class AddressService implements IAddressService {
    @Autowired
    private AddressRepository addressRepository;
    @Override
    public Address save(Address address) {
        return addressRepository.save(address);
    }
}
