package pl.my.e.sport.web.app.esportwebapp.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.my.e.sport.web.app.esportwebapp.domain.Player;
import pl.my.e.sport.web.app.esportwebapp.services.PlayerService;

import java.util.List;

@Slf4j
@RestController
public class PlayerController {

    private PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @RequestMapping(value = "/sayHello", method = RequestMethod.GET)
    public String sayHello() {
        return "hello world";
    }

    @PostMapping("/addNew")
    public Player addNew(@RequestParam("login") String login, @RequestParam("playerName") String playerName,
                         @RequestParam(value = "firstName", required = false) String firstName,
                         @RequestParam("lastName") String lastName) {
        return playerService.save(new Player(login, playerName, firstName, lastName));
    }

    @GetMapping("/findAll")
    public List<Player> findAll() {
        return playerService.listAll();
    }

    @GetMapping("/findById")
    public Player findById(@RequestParam("id") long id) {
        return playerService.findById(id);
    }

    @GetMapping("/findByLastName")
    public List<Player> findByLastName(@RequestParam("lastName") String lastName) {
        return playerService.findByLastName(lastName);

    }
}
