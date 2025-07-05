package co.simplon.jamixbusiness.commons;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    protected AbstractEntity() {
    }

    public Long getId() {
	return id;
    }

    @SuppressWarnings("unused")
    private void setId(Long id) {
	// this.id = id; genareted by db
    }

}
