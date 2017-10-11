package pl.my.e.sport.web.app.esportwebapp.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.my.e.sport.web.app.esportwebapp.domain.Player;
import pl.my.e.sport.web.app.esportwebapp.model.MyModel;
import pl.my.e.sport.web.app.esportwebapp.services.PlayerService;

import java.util.List;

@Slf4j
@RestController
public class PlayerController {

    private PlayerService playerService;
    private MyModel model;

    @Autowired
    public PlayerController(PlayerService playerService, MyModel model) {
        this.playerService = playerService;
        this.model = model;
    }

    @RequestMapping(value = "/sayHello", method = RequestMethod.GET)
    public String sayHello() {
        model.setName("hello world");
        return model.toString();
    }

    @RequestMapping("/save")
    public String process() {
        playerService.save(new Player("qwe14", "Peter", "Davis"));
        playerService.save(new Player("qwe13", "Adam", "Johnson"));
        playerService.save(new Player("qwe12", "Kim", "Smith"));
        playerService.save(new Player("qwe1", "David", "Williams"));
        playerService.save(new Player("qwe", "Jack", "Smith"));
        return "Done";
    }


    @RequestMapping("/findall")
    public String findAll() {
        List<Player> players = playerService.listAll();
        return players.toString();
    }

    @RequestMapping("/findbyid")
    public String findById(@RequestParam("id") long id) {
        String result = "";
        result = playerService.findById(id).toString();
        return result;
    }

    @RequestMapping("/findbylastname")
    public String fetchDataByLastName(@RequestParam("lastname") String lastName) {
        String result = "<html>";
        for (Player cust : playerService.findByLastName(lastName)) {
            result += "<div>" + cust.toString() + "</div>";
        }
        return result + "</html>";
    }
}
