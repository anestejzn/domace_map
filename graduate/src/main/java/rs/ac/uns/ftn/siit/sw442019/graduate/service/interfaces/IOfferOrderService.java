package rs.ac.uns.ftn.siit.sw442019.graduate.service.interfaces;

import rs.ac.uns.ftn.siit.sw442019.graduate.model.OfferOrder;
public interface IOfferOrderService {
    OfferOrder save(OfferOrder offerOrder);
    OfferOrder getById(Long id);
}
