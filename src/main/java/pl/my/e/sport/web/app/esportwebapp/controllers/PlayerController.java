package pl.my.e.sport.web.app.esportwebapp.controllers;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.my.e.sport.web.app.esportwebapp.domain.dto.PlayerDto;
import pl.my.e.sport.web.app.esportwebapp.domain.dto.PlayerMapper;
import pl.my.e.sport.web.app.esportwebapp.services.PlayerService;

import java.util.List;


@Log4j
@RestController
@RequestMapping("/player")
public class PlayerController {

    private PlayerService playerService;
    private PlayerMapper playerMapper;

    @Autowired
    public PlayerController(PlayerService playerService, PlayerMapper playerMapper) {
        this.playerService = playerService;
        this.playerMapper = playerMapper;
    }

    @PostMapping("/create")
    public PlayerDto create(@RequestParam("playerName") String playerName,
                            @RequestParam(value = "firstName", required = false) String firstName,
                            @RequestParam(value = "lastName", required = false) String lastName,
                            @RequestParam("teamId") Long teamId) {
        PlayerDto playerDto = new PlayerDto(playerName, firstName, lastName, teamId);
        return playerMapper.toDto(playerService.create(playerMapper.fromDto(playerDto)));
    }

    @GetMapping("/findAll")
    public List<PlayerDto> findAll() {
        return playerMapper.toDto(playerService.listAll());
    }

    @GetMapping("/findById")
    public PlayerDto findById(@RequestParam("id") Long id) {
        return playerMapper.toDto(playerService.findById(id));
    }

    @GetMapping("/findByLastName")
    public PlayerDto findByLastName(@RequestParam("lastName") String lastName) {
        return playerMapper.toDto(playerService.findByLastName(lastName));

    }

    @GetMapping("/findAllByTeamId/{id}")
    public List<PlayerDto> findAllByTeamId(@PathVariable("id") Long id) {
        return playerMapper.toDto(playerService.findAllByTeamId(id));
    }
}
