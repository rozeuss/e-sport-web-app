package pl.my.e.sport.web.app.esportwebapp.eventlisteners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.my.e.sport.web.app.esportwebapp.domain.Match;
import pl.my.e.sport.web.app.esportwebapp.domain.Team;
import pl.my.e.sport.web.app.esportwebapp.repositories.MatchRepository;

import java.util.List;
import java.util.Optional;

@Component
public class MatchEventListener {

    private MatchRepository matchRepository;

    @Autowired
    public MatchEventListener(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    @EventListener(condition = "#matchEvent.finalised")
    public void handleMatchScoresChanged(MatchEvent matchEvent) {
        Match match = matchEvent.getMatch();
        Boolean hasPlayoff = false;
        Optional<Match> playoffMatch = Optional.empty();
        if (match.getPhase() == 2) {
            playoffMatch = matchRepository.findByTournamentIdAndPhase(match.getTournament().getId(), 0);
            if (playoffMatch.isPresent()) {
                hasPlayoff = true;
            }
        }
        List<Match> precedingMatches = matchRepository.findAllByNextMatchId(match.getNextMatch().getId());
        Match nextMatch = matchRepository.findOne(match.getNextMatch().getId());
        Team winner;
        Team loser = null;
        if (match.getScoreHome() > match.getScoreAway()) {
            winner = match.getTeamHome();
            if (hasPlayoff) {
                loser = match.getTeamAway();
            }
        } else {
            winner = match.getTeamAway();
            if (hasPlayoff) {
                loser = match.getTeamHome();
            }
        }
        if (precedingMatches.get(1).getId() < precedingMatches.get(0).getId()) {
            nextMatch.setTeamHome(winner);
        } else {
            nextMatch.setTeamAway(winner);
        }
        matchRepository.save(nextMatch);
        if (hasPlayoff) {
            if (playoffMatch.get().getTeamHome() == null) {
                playoffMatch.get().setTeamHome(loser);
            } else {
                playoffMatch.get().setTeamAway(loser);
            }
            matchRepository.save(playoffMatch.get());
        }
    }


}
