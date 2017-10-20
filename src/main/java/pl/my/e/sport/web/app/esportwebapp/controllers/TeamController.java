package pl.my.e.sport.web.app.esportwebapp.controllers;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.my.e.sport.web.app.esportwebapp.domain.Team;
import pl.my.e.sport.web.app.esportwebapp.domain.dto.TeamDto;
import pl.my.e.sport.web.app.esportwebapp.domain.dto.TeamMapper;
import pl.my.e.sport.web.app.esportwebapp.services.TeamService;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Log4j
@RestController
@RequestMapping("/team")
public class TeamController {

    private TeamService teamService;
    private TeamMapper teamMapper;

    @Autowired
    public TeamController(TeamService teamService, TeamMapper teamMapper) {
        this.teamService = teamService;
        this.teamMapper = teamMapper;
    }

    @PostMapping("/create")
    public TeamDto create(TeamDto teamDto) {
        return teamMapper.toDto(teamService.create(teamMapper.fromDto(teamDto)));
    }

    @GetMapping("/findByName")
    public Team findByName(String name) {
        return teamService.findByName(name);
    }

    @GetMapping("/findById")
    public Team findById(Long id) {
        return teamService.findById(id);
    }

//    List<Team> listAll();
//
//    Team findByAccountId(Long id);
}
