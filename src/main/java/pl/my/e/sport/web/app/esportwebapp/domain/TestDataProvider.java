package pl.my.e.sport.web.app.esportwebapp.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.my.e.sport.web.app.esportwebapp.controllers.MatchController;
import pl.my.e.sport.web.app.esportwebapp.controllers.TournamentController;
import pl.my.e.sport.web.app.esportwebapp.domain.dto.TournamentDto;
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
    private TournamentController tournamentController;
    private MatchController matchController;

    @Autowired
    public TestDataProvider(PlayerRepository playerRepository, TeamRepository teamRepository,
                            TournamentRepository tournamentRepository, MatchRepository matchRepository,
                            AccountTypeRepository accountTypeRepository, AccountRepository accountRepository,
                            TournamentController tournamentController, MatchController matchController) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
        this.tournamentRepository = tournamentRepository;
        this.matchRepository = matchRepository;
        this.accountTypeRepository = accountTypeRepository;
        this.accountRepository = accountRepository;
        this.tournamentController = tournamentController;
        this.matchController = matchController;
    }

    @Override
    public void run(String... args) throws Exception {
        generateAccountTypes();
        generateAccounts();
        generateTeams();
        generatePlayers();
        generateTournaments();
        generateMatches();
        TournamentDto tournamentDto = new TournamentDto();
        tournamentDto
                .setDescription("Turniej CS:GO organizowany przez samorząd uczniowski WAT. Główny sponsor: POLBUDEX.");
        tournamentDto.setEndDate("2018-06-06");
        tournamentDto.setStartDate("2018-06-01");
        tournamentDto.setLocation("Polska, Radom");
        tournamentDto.setOrganizerId(1L);
        tournamentDto.setPrize(new BigDecimal(5000));
        tournamentDto.setTitle("WAT MASTERS 2018");
        TournamentDto tournamentDto1 = tournamentController.create(tournamentDto);
        matchController.createMatches(tournamentDto1.getId(), 8, false);
        tournamentController.signUpForTournament(tournamentDto1.getId(), 1L);
        tournamentController.signUpForTournament(tournamentDto1.getId(), 2L);
        tournamentController.signUpForTournament(tournamentDto1.getId(), 3L);
        tournamentController.signUpForTournament(tournamentDto1.getId(), 4L);
        tournamentController.signUpForTournament(tournamentDto1.getId(), 5L);
        tournamentController.signUpForTournament(tournamentDto1.getId(), 6L);
        tournamentController.signUpForTournament(tournamentDto1.getId(), 7L);
        tournamentController.signUpForTournament(tournamentDto1.getId(), 8L);

        TournamentDto tournamentDto2 = new TournamentDto();
        tournamentDto2.setDescription("Turniej CS:GO.");
        tournamentDto2.setEndDate("2018-06-06");
        tournamentDto2.setStartDate("2018-06-01");
        tournamentDto2.setLocation("Polska, Warszawa");
        tournamentDto2.setOrganizerId(1L);
        tournamentDto2.setPrize(new BigDecimal(10000));
        tournamentDto2.setTitle("CSGO CUP 2018");
        TournamentDto tournamentDto3 = tournamentController.create(tournamentDto2);
        matchController.createMatches(tournamentDto3.getId(), 4, true);


    }

    private Player generatePlayer(String playerName, String firstName, String lastName, Team team,
                                  Statistics statistics) {
        return new Player(playerName, firstName, lastName, team, null);
    }

    private void generatePlayers() {
        playerRepository.save(generatePlayer("player", "Lorem",
                "Ipsum", teamRepository.findOne(1L), null));
        playerRepository.save(generatePlayer("slayer", "Lorem",
                "Ipsum", teamRepository.findOne(1L), null));
        playerRepository.save(generatePlayer("ultraDzik", "Lorem",
                "Ipsum", teamRepository.findOne(1L), null));
        playerRepository.save(generatePlayer("ma100dont", "Lorem",
                "Ipsum", teamRepository.findOne(1L), null));
        playerRepository.save(generatePlayer("knur2000PL", "Lorem",
                "Ipsum", teamRepository.findOne(1L), null));
        playerRepository.save(generatePlayer("x.robak", "Lorem",
                "Ipsum", teamRepository.findOne(2L), null));
        playerRepository.save(generatePlayer("omewa shindeiru", "Lorem",
                "Ipsum", teamRepository.findOne(2L), null));
    }

    private Team generateTeam(String name, String country, Account account) {
        return new Team(name, country, account);
    }

    private void generateTeams() {
        teamRepository.save(generateTeam("GRACZE", "Poland", accountRepository.findOne(1L)));
        teamRepository.save(generateTeam("PLAYERS", "England", accountRepository.findOne(2L)));
        teamRepository.save(generateTeam("SPIELER", "Germany", accountRepository.findOne(3L)));
        teamRepository.save(generateTeam("SPELARE", "Sweden", accountRepository.findOne(4L)));
        teamRepository.save(generateTeam("CZIUJEN", "China", accountRepository.findOne(5L)));
        teamRepository.save(generateTeam("JOGADORES", "Brazil", accountRepository.findOne(6L)));
        teamRepository.save(generateTeam("IGROKI", "Russia", accountRepository.findOne(7L)));
        teamRepository.save(generateTeam("TRUEPLAYERS", "USA", accountRepository.findOne(8L)));
    }

    private Tournament generateTournament(String title, String description, String location,
                                          LocalDate startDate, LocalDate endDate, BigDecimal prize) {
        return new Tournament(title, description, location, startDate, endDate, prize,
                accountRepository.findOne(1L));
    }

    private void generateTournaments() {
    }

    private Match generateMatch(Match nextMatch, LocalDate startDate, Team teamAway, Team teamHome, Long scoreAway,
                                Long scoreHome, Tournament tournament, Integer phase) {
        return new Match(nextMatch, startDate, teamAway, teamHome, scoreAway, scoreHome, tournament, phase);
    }

    private void generateMatches() {

    }

    private void generateAccountTypes() {
        accountTypeRepository.save(new AccountType("TEAM_MANAGER"));
        accountTypeRepository.save(new AccountType("BOOKMAKER"));
    }

    private Account generateAccount(String email, String pass, AccountType accountType) {
        return new Account(email, pass, accountType);
    }

    private void generateAccounts() {
        accountRepository.save(generateAccount("abc@abc.pl", "test", accountTypeRepository.findOne(1L)));
        accountRepository.save(generateAccount("qaz@qaz.pl", "test", accountTypeRepository.findOne(1L)));
        accountRepository.save(generateAccount("qwe@qwe.pl", "test", accountTypeRepository.findOne(1L)));
        accountRepository.save(generateAccount("qsc@qsc.pl", "test", accountTypeRepository.findOne(1L)));
        accountRepository.save(generateAccount("1@qsc.pl", "test", accountTypeRepository.findOne(1L)));
        accountRepository.save(generateAccount("2@qsc.pl", "test", accountTypeRepository.findOne(1L)));
        accountRepository.save(generateAccount("3@qsc.pl", "test", accountTypeRepository.findOne(1L)));
        accountRepository.save(generateAccount("4@qsc.pl", "test", accountTypeRepository.findOne(1L)));
    }
}
