package rs.ac.uns.ftn.siit.sw442019.graduate.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.siit.sw442019.graduate.model.OfferOrder;
import rs.ac.uns.ftn.siit.sw442019.graduate.repository.OfferOrderRepository;
import rs.ac.uns.ftn.siit.sw442019.graduate.service.interfaces.IOfferOrderService;

@Service
public class OfferOrderService implements IOfferOrderService {
    @Autowired
    private OfferOrderRepository offerOrderRepository;

    @Override
    public OfferOrder save(OfferOrder offerOrder) {
        return offerOrderRepository.save(offerOrder);
    }

    @Override
    public OfferOrder getById(Long id) {
        return offerOrderRepository.getReferenceById(id);
    }
}
