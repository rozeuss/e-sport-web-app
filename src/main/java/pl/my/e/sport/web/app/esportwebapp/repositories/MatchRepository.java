package pl.my.e.sport.web.app.esportwebapp.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.my.e.sport.web.app.esportwebapp.domain.Match;
import pl.my.e.sport.web.app.esportwebapp.domain.Team;

import java.util.List;
import java.util.Optional;

@Repository
public interface MatchRepository extends CrudRepository<Match, Long> {

    Optional<Match> findMatchByPhase(Integer phase);

    List<Match> findAllByTournamentId(Long id);

    Optional<Match> findByTournamentIdAndPhase(Long tournamentId, Integer phase);

    List<Match> findAllByNextMatchId(Long nextMatchId);

    //    @Query(value = "FROM Match m WHERE m.teamHome = :teamId or m.teamAway = :teamId", nativeQuery = true)
    @Query("SELECT m FROM Match m WHERE (m.teamHome = :teamId or m.teamAway = :teamId)"
            + " and (m.scoreAway is not null and m.scoreHome is not null)")
    List<Match> findAllByTeamId(@Param("teamId") Team team);

    @Query("SELECT m FROM Match m WHERE (m.teamHome = :teamId or m.teamAway = :teamId) AND m.phase = :phaseNumber")
    List<Match> findAllByTeamIdAndPhase(@Param("teamId") Team team, @Param("phaseNumber") int phaseNumber);

}