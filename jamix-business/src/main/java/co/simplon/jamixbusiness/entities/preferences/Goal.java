package co.simplon.jamixbusiness.entities.preferences;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_goals")
public class Goal {
    @Id
    @Column(name = "goal_name")
    private String name;

    public Goal() {
    }

    public Goal(String name) {
	this.name = name;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }
}
