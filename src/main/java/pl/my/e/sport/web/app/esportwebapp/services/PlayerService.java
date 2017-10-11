package pl.my.e.sport.web.app.esportwebapp.services;

import pl.my.e.sport.web.app.esportwebapp.domain.Player;

import java.util.List;

public interface PlayerService {

    List<Player> listAll();

    List<Player> findByLastName(String lastName);

    Player findById(Long id);

    Player save(Player player);
}
