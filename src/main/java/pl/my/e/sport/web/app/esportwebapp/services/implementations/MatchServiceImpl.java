package pl.my.e.sport.web.app.esportwebapp.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.my.e.sport.web.app.esportwebapp.domain.Match;
import pl.my.e.sport.web.app.esportwebapp.domain.Tournament;
import pl.my.e.sport.web.app.esportwebapp.repositories.MatchRepository;
import pl.my.e.sport.web.app.esportwebapp.repositories.TeamRepository;
import pl.my.e.sport.web.app.esportwebapp.repositories.TournamentRepository;
import pl.my.e.sport.web.app.esportwebapp.services.MatchService;

import java.util.*;
import java.util.stream.IntStream;

@Service
public class MatchServiceImpl implements MatchService {

    private static final int PLAYOFF_PHASE = 0;
    private static final int[] participants = {4, 8, 16, 32};
    private MatchRepository matchRepository;
    private TeamRepository teamRepository;
    private TournamentRepository tournamentRepository;

    @Autowired
    public MatchServiceImpl(MatchRepository matchRepository, TeamRepository teamRepository,
                            TournamentRepository tournamentRepository) {
        this.matchRepository = matchRepository;
        this.teamRepository = teamRepository;
        this.tournamentRepository = tournamentRepository;
    }


    @Override
    public Match findById(Long id) {
        return matchRepository.findOne(id);
    }

    @Override
    public List<Match> createMatchesForTournament(Long tournamentId, Integer numberOfParticipants, Boolean hasPlayoff) {
        List<Match> matches = new ArrayList<>();

        Tournament tournament = tournamentRepository.findOne(tournamentId);
        if (tournament == null) {
            return null;
        }

        boolean isProperNumberOfParticipants =
                IntStream.of(participants).anyMatch(value -> value == numberOfParticipants);
        if (!isProperNumberOfParticipants) {
            return null;
        }

        Map<Integer, Integer> phases = preparePhasesForTournament(numberOfParticipants, hasPlayoff);
        List<Match> matchesInCurrentPhase = new ArrayList<>();
        for (Map.Entry<Integer, Integer> phase : phases.entrySet()) {
            List<Match> nextMatches = new ArrayList<>(matchesInCurrentPhase);
            matchesInCurrentPhase.clear();
            for (int matchNumber = 0; matchNumber < phase.getValue(); matchNumber++) {
                Match match = new Match();
                match.setTournament(tournament);
                match.setStartDate(tournament.getStartDate());
                match.setPhase(phase.getKey());
                if (!nextMatches.isEmpty()) {
                    match.setNextMatch(nextMatches.get((int) Math.floor(matchNumber / 2)));
                }
                Match createdMatch = create(match);
                if (phase.getKey() > 0) {
                    matchesInCurrentPhase.add(createdMatch);
                }
                matches.add(createdMatch);
            }
        }
        return matches;
    }

    @Override
    public Match create(Match match) {
        return matchRepository.save(match);
    }

    @Override
    public List<Match> findAllByTournamentId(Long id) {
        return matchRepository.findAllByTournamentId(id);
    }

    @Override
    public Match getPlayoff(Long tournamentId) {
        Tournament tournament = tournamentRepository.findOne(tournamentId);
        if (tournament == null) {
            return null;
        }
        Optional<Match> playoff = matchRepository.findByTournamentIdAndPhase(tournamentId, PLAYOFF_PHASE);
        return playoff.orElse(null);
    }

    private Map<Integer, Integer> preparePhasesForTournament(Integer numberOfParticipants, Boolean hasPlayoff) {
        TreeMap<Integer, Integer> phases = new TreeMap<>();
        int phase = (int) (Math.log(numberOfParticipants) / Math.log(2));
        while (phase > 0) {
            int numberOfMatches = numberOfParticipants /= 2;
            phases.put(phase, numberOfMatches);
            phase--;
        }
        if (hasPlayoff) {
            phases.put(0, 1);
        }
        return phases;
    }
}
