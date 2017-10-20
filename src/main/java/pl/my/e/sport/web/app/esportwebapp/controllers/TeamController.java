package pl.my.e.sport.web.app.esportwebapp.controllers;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.my.e.sport.web.app.esportwebapp.domain.Team;
import pl.my.e.sport.web.app.esportwebapp.services.TeamService;

@Log4j
@RestController
@RequestMapping("/team")
public class TeamController {

    private TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping("/addNew")
    public Team addNew(@RequestParam("teamName") String name, String email) {
        return teamService.save(new Team(name, email, null));
    }

    @GetMapping("/findByName")
    public Team findByEmail(String name) {
        return teamService.findByName(name);
    }

}
