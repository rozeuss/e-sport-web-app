package pl.my.e.sport.web.app.esportwebapp.services.implementations;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.my.e.sport.web.app.esportwebapp.domain.Player;
import pl.my.e.sport.web.app.esportwebapp.repositories.PlayerRepository;
import pl.my.e.sport.web.app.esportwebapp.services.PlayerService;

import java.util.ArrayList;
import java.util.List;

@Log4j
@Service
public class PlayerServiceImpl implements PlayerService {

    private static final int MAX_PLAYERS_IN_TEAM = 5;
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
    public Player findByLastName(String lastName) {
        return playerRepository.findByLastName(lastName);
    }

    @Override
    public Player findByPlayerName(String playerName) {
        return playerRepository.findByPlayerName(playerName);
    }

    @Override
    public List<Player> findAllByTeamId(Long id) {
        return playerRepository.findAllByTeam_Id(id);
    }

    @Override
    public Player findById(Long id) {
        return playerRepository.findOne(id);
    }

    @Override
    public Player create(Player player) {
        Long numberOfPlayersInTeam = playerRepository.countByTeam_Id(player.getTeam().getId());
        if (numberOfPlayersInTeam == MAX_PLAYERS_IN_TEAM) {
            return null;
        }
        return playerRepository.save(player);
    }
}
