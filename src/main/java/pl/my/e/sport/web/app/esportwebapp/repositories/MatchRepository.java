package pl.my.e.sport.web.app.esportwebapp.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.my.e.sport.web.app.esportwebapp.domain.Match;

import java.util.List;
import java.util.Optional;

@Repository
public interface MatchRepository extends CrudRepository<Match, Long> {

    Optional<Match> findMatchByPhase(Integer phase);

    List<Match> findAllByTournamentId(Long id);

    Optional<Match> findByTournamentIdAndPhase(Long tournamentId, Integer phase);
}