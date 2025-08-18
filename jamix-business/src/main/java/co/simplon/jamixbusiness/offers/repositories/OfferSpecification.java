package co.simplon.jamixbusiness.offers.repositories;

import co.simplon.jamixbusiness.offers.entities.Offer;
import java.time.LocalDate;
import org.springframework.data.jpa.domain.Specification;

/**
 * Reusable JPA Specifications for filtering Offer
 */
public class OfferSpecification {
    public static Specification<Offer> titleContains(String keyword) {
	return (root, query, cb) -> cb.like(cb.lower(root.get("title")), "%" + keyword.toLowerCase() + "%");
    }

    public static Specification<Offer> hasInstrument(Long instrumentId) {
	return (root, query, cb) -> cb.equal(root.join("instrument").get("id"), instrumentId);
    }

    public static Specification<Offer> hasStyle(Long styleId) {
	return (root, query, cb) -> cb.equal(root.join("style").get("id"), styleId);
    }

    public static Specification<Offer> hasGoal(Long goalId) {
	return (root, query, cb) -> cb.equal(root.join("goal").get("id"), goalId);
    }

    public static Specification<Offer> postedAfter(LocalDate date) {
	return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get("createdAt"), date);
    }

    public static Specification<Offer> hasCity(String city) {
	return (root, query, cb) -> cb.equal(cb.lower(root.join("location").get("city")), city.toLowerCase());
    }

    public static Specification<Offer> hasZipCode(String zipCode) {
	return (root, query, cb) -> cb.equal(root.join("location").get("zipCode"), zipCode);
    }

}
