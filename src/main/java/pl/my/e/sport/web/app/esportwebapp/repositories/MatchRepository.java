package pl.my.e.sport.web.app.esportwebapp.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.my.e.sport.web.app.esportwebapp.domain.Match;

import java.util.List;

@Repository
public interface MatchRepository extends CrudRepository<Match, Long> {

    Match findMatchByPhase(int phase);

    List<Match> findByTournament(long id);

}