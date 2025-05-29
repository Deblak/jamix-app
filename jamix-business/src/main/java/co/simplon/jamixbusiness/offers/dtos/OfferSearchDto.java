package co.simplon.jamixbusiness.offers.dtos;

import java.time.LocalDate;

public record OfferSearchDto(String title, Long instrumentId, Long styleId, Long goalId, LocalDate postedAfter,
	String sortDirection, Integer page, Integer size) {
    public String sortDirectionOrDefault() {
	return sortDirection != null ? sortDirection.toUpperCase() : "DESC";
    }

    /**
     * Request
     *
     * @return page index
     */
    public int pageOrDefault() {
	return page != null ? page : 0;
    }

    /**
     * Request
     *
     * @return page size
     */
    public int sizeOrDefault() {
	return size != null ? size : 10;
    }

}
