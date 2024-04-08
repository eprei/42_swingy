package ch._42lausanne.swingy.repository;

import ch._42lausanne.swingy.model.game.Stats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatRepository extends JpaRepository<Stats, Long> {
}
