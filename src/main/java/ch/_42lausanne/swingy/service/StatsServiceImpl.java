package ch._42lausanne.swingy.service;

import ch._42lausanne.swingy.model.game.Stats;
import ch._42lausanne.swingy.repository.StatRepository;
import jakarta.transaction.Transactional;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Setter
@Service
public class StatsServiceImpl implements StatsService {
    @Autowired
    private StatRepository statRepository;

    @Override
    @Transactional
    public Stats save(Stats stats) {
        System.out.printf("Saving stats %s\n", stats.toString());
        return statRepository.save(stats);
    }

}
