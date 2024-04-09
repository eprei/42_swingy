package ch._42lausanne.swingy.service;

import ch._42lausanne.swingy.model.characters.Hero;
import ch._42lausanne.swingy.repository.HeroRepository;
import jakarta.transaction.Transactional;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Setter
@Service
public class HeroServiceImpl implements HeroService {
    private final HeroRepository heroRepository;

    public HeroServiceImpl(HeroRepository heroRepository) {
        this.heroRepository = heroRepository;
    }

    @Override
    @Transactional
    public Hero save(Hero hero) {
        hero.restartHp();
        return heroRepository.save(hero);
    }

    @Override
    public List<Hero> findAll() {
        return heroRepository.findAll();
    }
}
