package co.simplon.jamixbusiness.offers.mappers;

import java.util.Map;

import org.springframework.stereotype.Component;

import co.simplon.jamixbusiness.offers.dtos.OfferSearchDto;

@Component
public class OfferSearchRequestMapper {

    public OfferSearchDto from(Map<String, String> params) {
	String title = params.get("title");
	Long instrumentId = parseLong(params.get("instrumentId"));
	Long styleId = parseLong(params.get("styleId"));
	Long goalId = parseLong(params.get("goalId"));
	String sortDirection = params.get("sortDirection");
	Integer page = parseInt(params.get("page"));
	Integer size = parseInt(params.get("size"));

	return new OfferSearchDto(title, instrumentId, styleId, goalId, null, sortDirection, page, size);
    }

    private Long parseLong(String value) {
	try {
	    return value != null ? Long.parseLong(value) : null;
	} catch (NumberFormatException e) {
	    return null;
	}
    }

    private Integer parseInt(String value) {
	try {
	    return value != null ? Integer.parseInt(value) : null;
	} catch (NumberFormatException e) {
	    return null;
	}
    }
}
