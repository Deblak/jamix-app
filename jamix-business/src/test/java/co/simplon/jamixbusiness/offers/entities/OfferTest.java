package co.simplon.jamixbusiness.offers.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.lang.reflect.Field;

import org.junit.jupiter.api.Test;

class OfferTest {
    /**
     * Helper method to set ID via reflection
     *
     * @param offer
     * @param id
     * @throws Exception
     */
    private void setId(Offer offer, Long id) throws Exception {
	Field idField = offer.getClass().getSuperclass().getDeclaredField("id");
	idField.setAccessible(true);
	idField.set(offer, id);
    }

    @Test
    void hashCode_should_be_equal() throws Exception {
	Offer offer1 = new Offer();
	setId(offer1, 1L);

	Offer offer2 = new Offer();
	setId(offer2, 1L);
	assertEquals(offer1.hashCode(), offer2.hashCode());
    }

    @Test
    void hashCode_should_not_be_equal() throws Exception {
	Offer offer1 = new Offer();
	setId(offer1, 1L);

	Offer offer2 = new Offer();
	setId(offer2, 2L);
	assertNotEquals(offer1.hashCode(), offer2.hashCode());
    }

    @Test
    void hashCode_should_be_consistent() throws Exception {
	Offer offer = new Offer();
	setId(offer, 1L);

	int initialHashCode = offer.hashCode();

	// Changing other field shouldn't affect hashCode
	offer.setTitle("New Title");
	assertEquals(initialHashCode, offer.hashCode());
    }

}