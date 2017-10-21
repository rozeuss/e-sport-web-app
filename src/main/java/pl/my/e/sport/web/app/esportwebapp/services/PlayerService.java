package pl.my.e.sport.web.app.esportwebapp.services;

import pl.my.e.sport.web.app.esportwebapp.domain.Player;

import java.util.List;

public interface PlayerService {

    List<Player> listAll();

    Player findByLastName(String lastName);

    Player findByPlayerName(String playerName);

    List<Player> findAllByTeamId(Long id);

    Player findById(Long id);

    Player create(Player player);
}
