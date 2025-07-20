package co.simplon.jamixbusiness.portfolios.dtos;

import java.util.List;

public record PortfolioFullDto(PortfolioDto portfolio, List<OfferLinkDto> offers) {

}
