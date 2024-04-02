package ch._42lausanne.swingy.model.presistence;

import ch._42lausanne.swingy.model.characters.Hero;
import org.springframework.data.repository.CrudRepository;

public interface HeroRepository extends CrudRepository<Hero, Long> {
}
