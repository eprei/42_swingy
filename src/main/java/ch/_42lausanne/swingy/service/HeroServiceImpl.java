package ch._42lausanne.swingy.service;

import ch._42lausanne.swingy.model.characters.Hero;
import ch._42lausanne.swingy.repository.HeroRepository;
import jakarta.transaction.Transactional;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Setter
@Service
public class HeroServiceImpl implements HeroService {
    @Autowired
    private HeroRepository heroRepository;

    @Override
    @Transactional
    public Hero save(Hero hero) {
        System.out.printf("--->\t\tSaving hero\t\t<---\n %s\n", hero.toString());
        return heroRepository.save(hero);
    }

    @Override
    public List<Hero> findAll() {
        return heroRepository.findAll();
    }
}
