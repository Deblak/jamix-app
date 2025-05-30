package co.simplon.jamixbusiness.offers.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import co.simplon.jamixbusiness.offers.dtos.OfferSearchDto;
import co.simplon.jamixbusiness.offers.dtos.OfferViewDto;
import co.simplon.jamixbusiness.offers.repositories.OfferRepository;
import co.simplon.jamixbusiness.offers.services.OfferSearchService;

@Service
public class OfferSearchServiceImpl implements OfferSearchService {
    private final OfferRepository repository;

    public OfferSearchServiceImpl(OfferRepository repository) {
	this.repository = repository;
    }

    @Override
    public List<OfferViewDto> findOffers(OfferSearchDto criteria) {
	// TODO Auto-generated method stub
	return null;
    }
}
