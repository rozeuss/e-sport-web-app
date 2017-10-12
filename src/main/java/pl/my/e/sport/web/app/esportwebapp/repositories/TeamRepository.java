package pl.my.e.sport.web.app.esportwebapp.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.my.e.sport.web.app.esportwebapp.domain.Team;

public interface TeamRepository extends CrudRepository<Team, Long> {
}