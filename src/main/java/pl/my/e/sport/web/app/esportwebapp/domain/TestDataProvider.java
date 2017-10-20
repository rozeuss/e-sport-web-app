package pl.my.e.sport.web.app.esportwebapp.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.my.e.sport.web.app.esportwebapp.repositories.MatchRepository;
import pl.my.e.sport.web.app.esportwebapp.repositories.PlayerRepository;
import pl.my.e.sport.web.app.esportwebapp.repositories.TeamRepository;
import pl.my.e.sport.web.app.esportwebapp.repositories.TournamentRepository;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
class TestDataProvider implements CommandLineRunner {

    private PlayerRepository playerRepository;
    private TeamRepository teamRepository;
    private TournamentRepository tournamentRepository;
    private MatchRepository matchRepository;

    @Autowired
    public TestDataProvider(PlayerRepository playerRepository, TeamRepository teamRepository,
                            TournamentRepository tournamentRepository,
                            MatchRepository matchRepository) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
        this.tournamentRepository = tournamentRepository;
        this.matchRepository = matchRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        generateTeams();
        generatePlayers();
        generateTournaments();
        generateMatches();
    }

    private Player generatePlayer(String playerName, String firstName, String lastName, Team team) {
        return new Player(playerName, firstName, lastName, team);
    }

    private void generatePlayers() {
        playerRepository.save(generatePlayer("player", "Dam",
                "Red", teamRepository.findOne(1L)));
        playerRepository.save(generatePlayer("slayer", "Dam1",
                "Red1", teamRepository.findOne(1L)));
        playerRepository.save(generatePlayer("ultraDzik", "Dam12",
                "Red12", teamRepository.findOne(1L)));
        playerRepository.save(generatePlayer("knur2000", "Dam123",
                "Red123", teamRepository.findOne(2L)));
        playerRepository.save(generatePlayer("miszcz_pl", "Dam1234",
                "Red1234", teamRepository.findOne(2L)));
    }

    private Team generateTeam(String name, String country) {
        return new Team(name, country, null);
    }

    private void generateTeams() {
        teamRepository.save(generateTeam("ONE", "Poland"));
        teamRepository.save(generateTeam("TWO", "Poland"));
        teamRepository.save(generateTeam("THREE", "England"));
    }

    private Tournament generateTournament(String title, String description, String location,
                                          LocalDate startDate, LocalDate endDate, BigDecimal prize) {
        return new Tournament(title, description, location, startDate, endDate, prize);
    }

    private void generateTournaments() {
        tournamentRepository.save(generateTournament("IEM KATOWICE",
                "Super festiwal.\n Nagroda 500zł", "Poland, Katowice",
                LocalDate.of(2017, 11, 1), LocalDate.of(2017, 11, 14),
                new BigDecimal(5000.253)));
    }

    private Match generateMatch(LocalDate startDate, Team teamAway, Team teamHome, long scoreAway,
                                long scoreHome, Tournament tournament) {
        return new Match(startDate, teamAway, teamHome, scoreAway, scoreHome, tournament);
    }

    private void generateMatches() {
        matchRepository.save(generateMatch(LocalDate.of(2017, 11, 1),
                teamRepository.findOne(1L), teamRepository.findOne(2L), 2, 1,
                tournamentRepository.findOne(1L)));
        matchRepository.save(generateMatch(LocalDate.of(2017, 11, 5),
                teamRepository.findOne(2L), teamRepository.findOne(1L), 0, 3,
                tournamentRepository.findOne(1L)));
    }
}
