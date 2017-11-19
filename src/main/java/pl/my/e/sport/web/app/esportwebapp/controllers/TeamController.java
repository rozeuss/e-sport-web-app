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

    @GetMapping("/findById/{teamId}")
    public TeamDto findById(@PathVariable("teamId") Long id) {
        return teamMapper.toDto(teamService.findById(id));
    }

    @GetMapping("/listAll")
    public List<TeamDto> listAll() {
        return teamMapper.toDto(teamService.listAll());
    }

    @GetMapping("/findByAccountId/{accountId}")
    public TeamDto findByAccountId(@PathVariable("accountId") Long accountId) {
        return teamMapper.toDto(teamService.findByAccountId(accountId));
    }

    @GetMapping("findAllSignedForTournament/{tournamentId}")
    public List<TeamDto> findAllSignedForTournament(@PathVariable("tournamentId") Long tournamentId) {
        return teamMapper.toDto(teamService.findAllSignedForTournament(tournamentId));
    }
}
