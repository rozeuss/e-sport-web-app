package pl.my.e.sport.web.app.esportwebapp.controllers;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.my.e.sport.web.app.esportwebapp.domain.dto.MatchDto;
import pl.my.e.sport.web.app.esportwebapp.domain.dto.MatchMapper;
import pl.my.e.sport.web.app.esportwebapp.services.MatchService;

import java.util.List;

@Log4j
@RestController
@RequestMapping("/match")
public class MatchController {

    private MatchService matchService;
    private MatchMapper matchMapper;

    @Autowired
    public MatchController(MatchService matchService, MatchMapper matchMapper) {
        this.matchService = matchService;
        this.matchMapper = matchMapper;
    }

    @PostMapping("/createMatches")
    public List<MatchDto> createMatches(@RequestParam("tournamentId") Long tournamentId,
                                        @RequestParam("participants") Integer participants,
                                        @RequestParam("hasPlayoff") Boolean hasPlayoff) {
        return matchMapper.toDto(matchService.createMatchesForTournament(tournamentId, participants, hasPlayoff));
    }

    @GetMapping("/getPlayoff/{tournamentId}")
    public MatchDto getPlayoff(@PathVariable("tournamentId") Long tournamentId) {
        return matchMapper.toDto(matchService.getPlayoff(tournamentId));
    }

    @GetMapping("/findById/{id}")
    public MatchDto getMatchById(@PathVariable("id") Long id) {
        return matchMapper.toDto(matchService.findById(id));
    }

    @GetMapping("/findAllByTournamentId/{id}")
    public List<MatchDto> findAllByTournamentId(@PathVariable("id") Long id) {
        return matchMapper.toDto(matchService.findAllByTournamentId(id));
    }

}
