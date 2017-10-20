package pl.my.e.sport.web.app.esportwebapp.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.my.e.sport.web.app.esportwebapp.domain.Team;

@Repository
public interface TeamRepository extends CrudRepository<Team, Long> {

    Team findByName(String name);

}