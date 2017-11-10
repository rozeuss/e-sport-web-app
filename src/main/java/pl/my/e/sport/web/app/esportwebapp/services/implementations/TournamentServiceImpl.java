package pl.my.e.sport.web.app.esportwebapp.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.my.e.sport.web.app.esportwebapp.domain.Account;
import pl.my.e.sport.web.app.esportwebapp.domain.Match;
import pl.my.e.sport.web.app.esportwebapp.domain.Team;
import pl.my.e.sport.web.app.esportwebapp.domain.Tournament;
import pl.my.e.sport.web.app.esportwebapp.repositories.AccountRepository;
import pl.my.e.sport.web.app.esportwebapp.repositories.MatchRepository;
import pl.my.e.sport.web.app.esportwebapp.repositories.TeamRepository;
import pl.my.e.sport.web.app.esportwebapp.repositories.TournamentRepository;
import pl.my.e.sport.web.app.esportwebapp.services.TournamentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class TournamentServiceImpl implements TournamentService {

    private TournamentRepository tournamentRepository;
    private AccountRepository accountRepository;
    private MatchRepository matchRepository;
    private TeamRepository teamRepository;

    @Autowired
    public TournamentServiceImpl(TournamentRepository tournamentRepository, AccountRepository accountRepository,
                                 MatchRepository matchRepository, TeamRepository teamRepository) {
        this.tournamentRepository = tournamentRepository;
        this.accountRepository = accountRepository;
        this.matchRepository = matchRepository;
        this.teamRepository = teamRepository;
    }

    @Override
    public List<Tournament> listAll() {
        List<Tournament> tournaments = new ArrayList<>();
        tournamentRepository.findAll().forEach(tournaments::add);
        return tournaments;
    }

    @Override
    public Tournament findById(Long id) {
        return tournamentRepository.findOne(id);
    }

    @Override
    public Tournament create(Tournament tournament) {
        if (!tournament.getEndDate().isAfter(tournament.getStartDate())) {
            return null;
        } else {
            //TODO ponizsze w celach testowych, pozniej pobierane z tokena
            Account one = accountRepository.findOne(1L);
            tournament.setOrganizer(one);
            return tournamentRepository.save(tournament);
        }
    }

    @Override
    public List<Tournament> findAllByOrganizer(Long accountId) {
        return tournamentRepository.findAllByOrganizerId(accountId);
    }

    @Override
    public Boolean signUpForTournament(Long tournamentId, Long teamId) {
//        if (isTeamAlreadySignedForTournament(tournamentId, teamId)) {
//            return Boolean.FALSE;
//        }
// @TODO ODKOMENTOWAC JAK GOTOWE
        Optional<Tournament> tournament = Optional.ofNullable(tournamentRepository.findOne(tournamentId));
        Optional<Team> team = Optional.ofNullable(teamRepository.findOne(teamId));
        if (tournament.isPresent() && team.isPresent()) {
            List<Match> matches = matchRepository.findAllByTournamentId(tournamentId);
            if (matches.isEmpty()) {
                return Boolean.FALSE;
            }
            int openingPhase = matches.stream().mapToInt(Match::getPhase).max().getAsInt();
            List<Match> filteredMatches = matches.stream()
                    .filter(match -> match.getPhase() == openingPhase
                            && (match.getTeamHome() == null || match.getTeamAway() == null))
                    .collect(Collectors.toList());
            if (filteredMatches.isEmpty()) {
                return Boolean.FALSE;
            }
            int randomAvailableMatch = new Random().nextInt(filteredMatches.size());
            Match match = filteredMatches.get(randomAvailableMatch);
            if (match.getTeamHome() == null) {
                match.setTeamHome(team.get());
            } else {
                match.setTeamAway(team.get());
            }
            matchRepository.save(match);
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    @Override
    public Boolean isTeamAlreadySignedForTournament(Long tournamentId, Long teamId) {
        List<Match> matches = matchRepository.findAllByTournamentId(tournamentId);
        List<Match> collect = matches.stream().filter(match ->
                (match.getTeamAway() != null && match.getTeamAway().getId() == teamId)
                        ||
                        (match.getTeamHome() != null && match.getTeamHome()
                                .getId() == teamId)).collect(Collectors.toList());
        return !collect.isEmpty();
    }
}
