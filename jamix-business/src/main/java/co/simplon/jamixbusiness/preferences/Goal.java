package co.simplon.jamixbusiness.preferences;

import co.simplon.jamixbusiness.commons.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_goals")
public class Goal extends AbstractEntity {

    @Column(name = "goal_type", nullable = false, unique = true, updatable = false)
    private String type;

    public Goal() {
    }

    public Goal(String type) {
	this.type = type;
    }

    public String getType() {
	return type;
    }

    public void setType(String type) {
	this.type = type;
    }
}
