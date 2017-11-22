package pl.my.e.sport.web.app.esportwebapp.services;

import pl.my.e.sport.web.app.esportwebapp.domain.Match;
import pl.my.e.sport.web.app.esportwebapp.domain.TeamStatisticsReport;

import java.util.List;

public interface MatchService {

    Match findById(Long id);

    List<Match> createMatchesForTournament(Long tournamentId, Integer numberOfParticipants, Boolean hasPlayoff);

    Match create(Match match);

    Match getPlayoff(Long tournamentId);

    List<Match> findAllByTournamentId(Long id);

    Match update(Long id, Match match);

    List<Match> findAllByNextMatchId(Long nextMatchId);

    TeamStatisticsReport getTeamStatistics(Long teamId);

}
