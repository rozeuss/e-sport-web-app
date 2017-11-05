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
    private MatchRepository matchRepository;
    private AccountTypeRepository accountTypeRepository;
    private AccountRepository accountRepository;

    @Autowired
    public TestDataProvider(PlayerRepository playerRepository, TeamRepository teamRepository,
                            TournamentRepository tournamentRepository, MatchRepository matchRepository,
                            AccountTypeRepository accountTypeRepository, AccountRepository accountRepository) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
        this.tournamentRepository = tournamentRepository;
        this.matchRepository = matchRepository;
        this.accountTypeRepository = accountTypeRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        generateAccountTypes();
        generateAccounts();
        generateTeams();
        generatePlayers();
        generateTournaments();
        generateMatches();

    }

    private Player generatePlayer(String playerName, String firstName, String lastName, Team team,
                                  Statistics statistics) {
        return new Player(playerName, firstName, lastName, team, null);
    }

    private void generatePlayers() {
        playerRepository.save(generatePlayer("player", "Dam",
                "Red", teamRepository.findOne(1L), null));
        playerRepository.save(generatePlayer("slayer", "Dam1",
                "Red1", teamRepository.findOne(1L), null));
        playerRepository.save(generatePlayer("ultraDzik", "Dam12",
                "Red12", teamRepository.findOne(1L), null));
        playerRepository.save(generatePlayer("knur2000", "Dam123",
                "Red123", teamRepository.findOne(2L), null));
        playerRepository.save(generatePlayer("miszcz_pl", "Dam1234",
                "Red1234", teamRepository.findOne(2L), null));
    }

    private Team generateTeam(String name, String country, Account account) {
        return new Team(name, country, account);
    }

    private void generateTeams() {
        teamRepository.save(generateTeam("ONE", "Poland", accountRepository.findOne(1L)));
        teamRepository.save(generateTeam("TWO", "Poland", accountRepository.findOne(2L)));
        teamRepository.save(generateTeam("THREE", "England", accountRepository.findOne(3L)));
        teamRepository.save(generateTeam("FOUR", "England", accountRepository.findOne(4L)));
    }

    private Tournament generateTournament(String title, String description, String location,
                                          LocalDate startDate, LocalDate endDate, BigDecimal prize) {
        return new Tournament(title, description, location, startDate, endDate, prize,
                accountRepository.findOne(1L));
    }

    private void generateTournaments() {
        tournamentRepository.save(generateTournament("IEM KATOWICE",
                "Super festiwal.\n Nagroda 500zł", "Poland, Katowice",
                LocalDate.of(2017, 11, 1), LocalDate.of(2017, 11, 14),
                new BigDecimal(5000.253)));
        tournamentRepository.save(generateTournament("RADOM JADOM",
                "Super festiwal.\n Nagroda 5zł", "Poland, Radom",
                LocalDate.of(2017, 12, 1), LocalDate.of(2017, 12, 5),
                new BigDecimal(400)));
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

    private void generateAccountTypes() {
        accountTypeRepository.save(new AccountType("TEAM_MANAGER"));
        accountTypeRepository.save(new AccountType("BOOKMAKER"));
    }

    private Account generateAccount(String email, String pass, AccountType accountType) {
        return new Account(email, pass, accountType);
    }

    private void generateAccounts() {
        accountRepository.save(generateAccount("abc@abc.pl", "pass", accountTypeRepository.findOne(1L)));
        accountRepository.save(generateAccount("qaz@qaz.pl", "pass", accountTypeRepository.findOne(1L)));
        accountRepository.save(generateAccount("qwe@qwe.pl", "pass", accountTypeRepository.findOne(1L)));
        accountRepository.save(generateAccount("qsc@qsc.pl", "pass", accountTypeRepository.findOne(1L)));
        accountRepository.save(generateAccount("test@test.pl", "pass", accountTypeRepository.findOne(1L)));
    }
}
