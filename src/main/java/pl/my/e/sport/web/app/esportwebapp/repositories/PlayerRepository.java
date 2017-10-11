package pl.my.e.sport.web.app.esportwebapp.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.my.e.sport.web.app.esportwebapp.domain.Player;

import java.util.List;

public interface PlayerRepository extends CrudRepository<Player, Long> {

    List<Player> findByLastName(String lastName);

}