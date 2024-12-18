package ch._42lausanne.swingy.repository;

import ch._42lausanne.swingy.model.characters.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeroRepository extends JpaRepository<Hero, Long> {
}
