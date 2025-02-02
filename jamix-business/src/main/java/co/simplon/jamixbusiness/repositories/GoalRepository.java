package co.simplon.jamixbusiness.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.jamixbusiness.entities.Goal;

public interface GoalRepository extends JpaRepository<Goal, Long> {

    List<Goal> findByTypeIn(List<String> goalTypes);

}
