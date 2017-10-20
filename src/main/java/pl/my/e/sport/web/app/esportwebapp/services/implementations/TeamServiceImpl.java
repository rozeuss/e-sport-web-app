package pl.my.e.sport.web.app.esportwebapp.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.my.e.sport.web.app.esportwebapp.domain.Team;
import pl.my.e.sport.web.app.esportwebapp.repositories.TeamRepository;
import pl.my.e.sport.web.app.esportwebapp.services.TeamService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeamServiceImpl implements TeamService {

    private TeamRepository teamRepository;

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
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
            return new Team();
        }
        return teamRepository.save(team);
    }

    @Override
    public Team findByName(String name) {
        Optional<Team> teamByName = teamRepository.findByName(name);
        if (!teamByName.isPresent()) {
            return new Team(); //zwraca pusty obiekt z id = 0, reszta null
        }
        return teamByName.get();
    }


    @Override
    public Team findByAccountId(Long id) {
        Optional<Team> teamByAccountId = teamRepository.findByAccount_Id(id);
        if (!teamByAccountId.isPresent()) {
            return null; //zwraca no content, ktore podejscie lepsze nie wiem @TODO RACZEJ NULL LEPSZY BO HTTP.NORESPONSE
        }
        return teamByAccountId.get();
    }
}
