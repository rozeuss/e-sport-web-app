package pl.my.e.sport.web.app.esportwebapp.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.my.e.sport.web.app.esportwebapp.domain.Team;
import pl.my.e.sport.web.app.esportwebapp.services.TeamService;

@Slf4j
@RestController
@RequestMapping("/team")
public class TeamController {

    private TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping("/addNew")
    public Team addNew(@RequestParam("teamName") String name) {
        return teamService.save(new Team(name));
    }
//
//    @GetMapping("/findAll")
//    public List<Team> findAll() {
//        return teamService.listAll();
//    }
//
//    @GetMapping("/findById")
//    public Team findById(@RequestParam("id") long id) {
//        return teamService.findById(id);
//    }
}
