package pl.my.e.sport.web.app.esportwebapp.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.my.e.sport.web.app.esportwebapp.domain.Match;
import pl.my.e.sport.web.app.esportwebapp.domain.Team;
import pl.my.e.sport.web.app.esportwebapp.domain.TeamStatisticsReport;
import pl.my.e.sport.web.app.esportwebapp.domain.Tournament;
import pl.my.e.sport.web.app.esportwebapp.repositories.MatchRepository;
import pl.my.e.sport.web.app.esportwebapp.repositories.TeamRepository;
import pl.my.e.sport.web.app.esportwebapp.repositories.TournamentRepository;
import pl.my.e.sport.web.app.esportwebapp.services.MatchService;

import java.util.*;
import java.util.stream.IntStream;

@Service
public class MatchServiceImpl implements MatchService {

    private static final int[] PARTICIPANTS = {4, 8, 16, 32};
    private static final int FINAL_PHASE = 1;
    private static final int PLAYOFF_PHASE = 0;
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
                IntStream.of(PARTICIPANTS).anyMatch(value -> value == numberOfParticipants);
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
    public Match update(Long id, Match match) {
        Match foundMatch = findById(id);
        if (foundMatch == null) {
            return null;
        }
        if (match.getStartDate() != null) {
            foundMatch.setStartDate(match.getStartDate());
        }
        if (match.getScoreAway() != null) {
            foundMatch.setScoreAway(match.getScoreAway());
        }
        if (match.getScoreHome() != null) {
            foundMatch.setScoreHome(match.getScoreHome());
        }
        matchRepository.save(foundMatch);
        return foundMatch;
    }

    @Override
    public List<Match> findAllByNextMatchId(Long nextMatchId) {
        return matchRepository.findAllByNextMatchId(nextMatchId);
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

    @Override
    public TeamStatisticsReport getTeamStatistics(Long teamId) {
        Team team = teamRepository.findOne(teamId);
        if (team == null) {
            return null;
        }
        List<Match> teamMatches = matchRepository.findAllByTeamId(team);
        Integer numberOfPlayedMatches = teamMatches.size();
        Integer numberOfWonMatches = countMatches(team, teamMatches);
        Integer numberOfLostMatches = numberOfPlayedMatches - numberOfWonMatches;
        List<Match> finalMatches = matchRepository.findAllByTeamIdAndPhase(team, FINAL_PHASE);
        Integer numberOfFirstPlaces = countMatches(team, finalMatches);
        Integer numberOfSecondPlaces = finalMatches.size() - numberOfFirstPlaces;
        List<Match> playoffMatches = matchRepository.findAllByTeamIdAndPhase(team, PLAYOFF_PHASE);
        Integer numberOfThirdPlaces = countMatches(team, playoffMatches);
        return new TeamStatisticsReport(numberOfPlayedMatches, numberOfWonMatches, numberOfLostMatches,
                numberOfFirstPlaces, numberOfSecondPlaces, numberOfThirdPlaces);
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

    private Integer countMatches(Team team, List<Match> finalMatches) {
        Integer counter = 0;
        for (Match match : finalMatches) {
            if (match.getTeamHome().equals(team)) {
                if (match.getScoreHome() > match.getScoreAway()) {
                    counter++;
                }
            } else if (match.getTeamAway().equals(team)) {
                if (match.getScoreHome() < match.getScoreAway()) {
                    counter++;
                }
            }
        }
        return counter;
    }
}
