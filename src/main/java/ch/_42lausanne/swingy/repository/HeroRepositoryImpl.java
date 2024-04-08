package ch._42lausanne.swingy.repository;

import ch._42lausanne.swingy.model.characters.Hero;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class HeroRepositoryImpl implements HeroRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Hero save(Hero hero) {
        entityManager.persist(hero);
        return hero;
    }
}
