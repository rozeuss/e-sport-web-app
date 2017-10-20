package pl.my.e.sport.web.app.esportwebapp.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.my.e.sport.web.app.esportwebapp.domain.Team;

import java.util.Optional;

@Repository
public interface TeamRepository extends CrudRepository<Team, Long> {

    Optional<Team> findByName(String name);

    Optional<Team> findByAccount_Id(Long id);
}