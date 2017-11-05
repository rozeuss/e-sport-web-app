package pl.my.e.sport.web.app.esportwebapp.controllers;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.my.e.sport.web.app.esportwebapp.domain.dto.TournamentDto;
import pl.my.e.sport.web.app.esportwebapp.domain.dto.TournamentMapper;
import pl.my.e.sport.web.app.esportwebapp.services.TournamentService;

import java.util.List;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Log4j
@RestController
@RequestMapping("/tournament")
public class TournamentController {

    private TournamentService tournamentService;
    private TournamentMapper tournamentMapper;

    @Autowired
    public TournamentController(TournamentService tournamentService, TournamentMapper tournamentMapper) {
        this.tournamentService = tournamentService;
        this.tournamentMapper = tournamentMapper;
    }

    @PostMapping("/create")
    public @ResponseBody
    TournamentDto create(@RequestBody TournamentDto tournamentDto) {
        return tournamentMapper.toDto(tournamentService.create(tournamentMapper.fromDto(tournamentDto)));
    }

    @GetMapping("/findById/{id}")
    public TournamentDto findById(@PathVariable("id") Long id) {
        return tournamentMapper.toDto(tournamentService.findById(id));
    }

    @GetMapping("/listAll")
    public List<TournamentDto> listAll() {
        return tournamentMapper.toDto(tournamentService.listAll());
    }

    @GetMapping("/findAllByOrganizer/{id}")
    public List<TournamentDto> findAllByOrganizer(@PathVariable("id") Long accountId) {
        return tournamentMapper.toDto(tournamentService.findAllByOrganizer(accountId));
    }
}
