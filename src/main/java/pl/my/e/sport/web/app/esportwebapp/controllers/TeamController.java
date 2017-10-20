package pl.my.e.sport.web.app.esportwebapp.controllers;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.my.e.sport.web.app.esportwebapp.domain.dto.TeamDto;
import pl.my.e.sport.web.app.esportwebapp.domain.dto.TeamMapper;
import pl.my.e.sport.web.app.esportwebapp.services.TeamService;

import java.util.List;

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
    public TeamDto create(@RequestParam String name, @RequestParam String country) {
        TeamDto teamDto = new TeamDto(name, country);
        return teamMapper.toDto(teamService.create(teamMapper.fromDto(teamDto)));
    }

    @GetMapping("/findByName")
    public TeamDto findByName(String name) {
        return teamMapper.toDto(teamService.findByName(name));
    }

    @GetMapping("/findById")
    public TeamDto findById(Long id) {
        return teamMapper.toDto(teamService.findById(id));
    }

    @GetMapping("/listAll")
    public List<TeamDto> listAll() {
        return teamMapper.toDto(teamService.listAll());
    }

    @GetMapping("/findByAccountId")
    public TeamDto findByAccountId(Long id) {
        return teamMapper.toDto(teamService.findByAccountId(id));
    }
}
