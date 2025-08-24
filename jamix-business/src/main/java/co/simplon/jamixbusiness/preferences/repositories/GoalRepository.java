package co.simplon.jamixbusiness.preferences.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.jamixbusiness.preferences.entities.Goal;

public interface GoalRepository extends JpaRepository<Goal, Long> {

    List<Goal> findByTypeIn(List<String> goalTypes);

}
