package pl.my.e.sport.web.app.esportwebapp.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.my.e.sport.web.app.esportwebapp.domain.Match;
import pl.my.e.sport.web.app.esportwebapp.domain.Team;
import pl.my.e.sport.web.app.esportwebapp.repositories.MatchRepository;
import pl.my.e.sport.web.app.esportwebapp.repositories.TeamRepository;
import pl.my.e.sport.web.app.esportwebapp.services.TeamService;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TeamServiceImpl implements TeamService {

    private TeamRepository teamRepository;
    private MatchRepository matchRepository;

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository, MatchRepository matchRepository) {
        this.teamRepository = teamRepository;
        this.matchRepository = matchRepository;
    }

    @Override
    public List<Team> listAll() {
        List<Team> teams = new ArrayList<>();
        teamRepository.findAll().forEach(teams::add);
        return teams;
    }

    @Override
    public Team findById(Long id) {
        return teamRepository.findOne(id);
    }

    @Override
    public Team create(Team team) {
        Optional<Team> teamByName = teamRepository.findByName(team.getName());
        Optional<Team> teamByAccountId = teamRepository.findByAccount_Id(team.getAccount().getId());
        if (teamByName.isPresent() || teamByAccountId.isPresent()) {
            return null;
        }
        return teamRepository.save(team);
    }

    @Override
    public Team findByName(String name) {
        Optional<Team> teamByName = teamRepository.findByName(name);
        return teamByName.orElse(null);
    }


    @Override
    public Team findByAccountId(Long id) {
        Optional<Team> teamByAccountId = teamRepository.findByAccount_Id(id);
        return teamByAccountId.orElse(null);
    }

    @Override
    public List<Team> findAllSignedForTournament(Long tournamentId) {
        List<Match> allByTournamentId = matchRepository.findAllByTournamentId(tournamentId);
        Set<Team> participants = new HashSet<>();
        Optional.ofNullable(allByTournamentId)
                .orElseGet(Collections::emptyList)
                .stream()
                .filter(match -> Objects.nonNull(match.getTeamAway()) || Objects.nonNull(match.getTeamHome()))
                .forEach(match -> {
                    participants.add(match.getTeamAway());
                    participants.add(match.getTeamHome());
                });
        return participants.stream().filter(Objects::nonNull).collect(Collectors.toList());
    }
}
