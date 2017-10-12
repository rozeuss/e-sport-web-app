package pl.my.e.sport.web.app.esportwebapp.services;

import pl.my.e.sport.web.app.esportwebapp.domain.Team;

import java.util.List;

public interface TeamService {

    List<Team> listAll();

    Team findById(Long id);

    Team save(Team team);
}
