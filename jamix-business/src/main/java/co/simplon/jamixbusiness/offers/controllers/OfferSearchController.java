package co.simplon.jamixbusiness.offers.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/offers")
public class OfferSearchController {

    // private final OfferSearchService service;
//    private final OfferSearchRequestMapper mapper;
//
//    public OfferSearchController(OfferSearchService service, OfferSearchRequestMapper mapper) {
//	this.service = service;
//	this.mapper = mapper;
//    }

//    @GetMapping("/search")
//    public ResponseEntity<Page<OfferViewDto>> searchOffers(@RequestParam Map<String, String> requestParams) {
//	OfferSearchDto criteria = mapper.from(requestParams);
//	Page<OfferViewDto> results = service.searchOffers(criteria);
//	return ResponseEntity.ok(results);
//    }

}
