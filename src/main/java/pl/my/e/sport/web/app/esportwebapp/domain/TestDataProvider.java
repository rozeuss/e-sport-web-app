package pl.my.e.sport.web.app.esportwebapp.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.my.e.sport.web.app.esportwebapp.repositories.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
class TestDataProvider implements CommandLineRunner {

    private PlayerRepository playerRepository;
    private TeamRepository teamRepository;
    private TournamentRepository tournamentRepository;
    private StatisticsRepository statisticsRepository;
    private MatchRepository matchRepository;

    @Autowired
    public TestDataProvider(PlayerRepository playerRepository, TeamRepository teamRepository,
                            TournamentRepository tournamentRepository, StatisticsRepository statisticsRepository,
                            MatchRepository matchRepository) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
        this.tournamentRepository = tournamentRepository;
        this.statisticsRepository = statisticsRepository;
        this.matchRepository = matchRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        generateTeams();
        generatePlayers();
        generateTournaments();
        generateMatches();
    }

    private Player generatePlayer(String login, String playerName, String firstName, String lastName, Team team) {
        return new Player(login, playerName, firstName, lastName, team);
    }

    private void generatePlayers() {
        playerRepository.save(generatePlayer("abc", "player", "Dam",
                "Red", teamRepository.findOne(1L)));
        playerRepository.save(generatePlayer("abc1", "slayer", "Dam1",
                "Red1", teamRepository.findOne(1L)));
        playerRepository.save(generatePlayer("abc12", "ultraDzik", "Dam12",
                "Red12", teamRepository.findOne(1L)));
        playerRepository.save(generatePlayer("abc123", "knur2000", "Dam123",
                "Red123", teamRepository.findOne(2L)));
        playerRepository.save(generatePlayer("abc1234", "miszcz_pl", "Dam1234",
                "Red1234", teamRepository.findOne(2L)));
    }

    private Team generateTeam(String name, String email, String password, String country, Statistics statistics) {
        return new Team(name, email, password, country, statistics);
    }

    private void generateTeams() {
        teamRepository.save(generateTeam("ONE", "one@example.com",
                "pass", "Poland", null));
        teamRepository.save(generateTeam("TWO", "two@example.com",
                "pass", "Poland", null));
        teamRepository.save(generateTeam("THREE", "three@example.com",
                "pass", "England", null));
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
