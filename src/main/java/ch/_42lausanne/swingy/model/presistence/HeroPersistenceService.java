package ch._42lausanne.swingy.model.presistence;

import ch._42lausanne.swingy.model.characters.Hero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeroPersistenceService {
    @Autowired
    private HeroRepository heroRepository;

    public void saveHero(Hero hero) {
        heroRepository.save(hero);
    }

    public List<Hero> getAllHeros() {
        return (List<Hero>) heroRepository.findAll();
    }

    public Hero getHeroById(Long id) {
        return heroRepository.findById(id).orElse(null);
    }
}
