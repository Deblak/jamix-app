package co.simplon.jamixbusiness.preferences;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GoalRepository extends JpaRepository<Goal, Long> {

    List<Goal> findByTypeIn(List<String> goalTypes);

}
