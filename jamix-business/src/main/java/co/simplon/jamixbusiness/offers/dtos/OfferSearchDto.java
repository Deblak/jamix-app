package co.simplon.jamixbusiness.offers.dtos;

import java.time.LocalDate;

public record OfferSearchDto(String title, Long locationId, Long instrumentId, Long styleId, Long goalId, String city,
	String zipCode, String departementCode, LocalDate postedAfter, String sortDirection, Integer page,
	Integer size) {

    public String sortDirectionOrDefault() {
	return sortDirection != null ? sortDirection.toUpperCase() : "DESC";
    }

    public int pageOrDefault() {
	return page != null ? page : 0;
    }

    public int sizeOrDefault() {
	return size != null ? size : 10;
    }

}
