package pl.my.e.sport.web.app.esportwebapp.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.my.e.sport.web.app.esportwebapp.domain.Player;

import java.util.List;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Long> {

    Player findByLastName(String lastName);

    Player findByPlayerName(String playerName);

    List<Player> findAllByTeam_Id(Long id);

    Long countByTeam_Id(Long id);
}