package pl.my.e.sport.web.app.esportwebapp.domain;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.my.e.sport.web.app.esportwebapp.repositories.PlayerRepository;

@Component
class TestDataProvider implements CommandLineRunner {

    private PlayerRepository playerRepository;

    @Autowired
    public TestDataProvider(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        generatePlayers();
    }

    private Player generatePlayer(String login, String playerName, String firstName, String lastName) {
        return new Player(login, playerName, firstName, lastName);
    }

    private void generatePlayers() {
        playerRepository.save(generatePlayer("abc", "player", "Dam", "Red"));
        playerRepository.save(generatePlayer("abc1", "slayer", "Dam1", "Red1"));
        playerRepository.save(generatePlayer("abc12", "ultraDzik", "Dam12", "Red12"));
        playerRepository.save(generatePlayer("abc123", "knur2000", "Dam123", "Red123"));
        playerRepository.save(generatePlayer("abc1234", "miszcz_pl", "Dam1234", "Red1234"));
    }
}
