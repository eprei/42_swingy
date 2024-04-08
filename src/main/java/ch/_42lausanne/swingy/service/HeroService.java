package ch._42lausanne.swingy.service;

import ch._42lausanne.swingy.model.characters.Hero;

import java.util.List;

public interface HeroService {
    Hero save(Hero hero);

    List<Hero> findAll();
}
