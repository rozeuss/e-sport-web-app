package pl.my.e.sport.web.app.esportwebapp.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.my.e.sport.web.app.esportwebapp.domain.Player;
import pl.my.e.sport.web.app.esportwebapp.repositories.PlayerRepository;
import pl.my.e.sport.web.app.esportwebapp.services.PlayerService;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {

    private PlayerRepository playerRepository;

    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public List<Player> listAll() {
        List<Player> players = new ArrayList<>();
        playerRepository.findAll().forEach(players::add);
        return players;
    }

    @Override
    public List<Player> findByLastName(String lastName) {
        return playerRepository.findByLastName(lastName);
    }

    @Override
    public Player findById(Long id) {
        return playerRepository.findOne(id);
    }

    @Override
    public Player save(Player player) {
        return playerRepository.save(player);
    }
}
