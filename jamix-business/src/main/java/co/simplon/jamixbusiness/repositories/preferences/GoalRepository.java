package co.simplon.jamixbusiness.repositories.preferences;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.jamixbusiness.entities.preferences.Goal;

public interface GoalRepository extends JpaRepository<Goal, String> {

    List<Goal> findByNameIn(List<String> goalNames);

}
