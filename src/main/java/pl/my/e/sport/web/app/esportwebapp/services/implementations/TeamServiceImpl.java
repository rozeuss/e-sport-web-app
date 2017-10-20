package pl.my.e.sport.web.app.esportwebapp.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.my.e.sport.web.app.esportwebapp.domain.Team;
import pl.my.e.sport.web.app.esportwebapp.repositories.TeamRepository;
import pl.my.e.sport.web.app.esportwebapp.services.TeamService;

import java.util.ArrayList;
import java.util.List;

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
        return teamRepository.save(team);
    }

    @Override
    public Team findByName(String name) {
        return teamRepository.findByName(name);
    }

    @Override
    public Team findByAccountId(Long id) {
        return teamRepository.findByAccount_Id(id);
    }
}
