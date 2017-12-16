package pl.my.e.sport.web.app.esportwebapp.controllers;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;
import pl.my.e.sport.web.app.esportwebapp.domain.dto.MatchDto;
import pl.my.e.sport.web.app.esportwebapp.domain.dto.MatchMapper;
import pl.my.e.sport.web.app.esportwebapp.eventlisteners.MatchEvent;
import pl.my.e.sport.web.app.esportwebapp.services.MatchService;

import java.util.List;

@Log4j
@RestController
@RequestMapping("/match")
public class MatchController {

    private MatchService matchService;
    private MatchMapper matchMapper;
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public MatchController(MatchService matchService, MatchMapper matchMapper,
                           ApplicationEventPublisher applicationEventPublisher) {
        this.matchService = matchService;
        this.matchMapper = matchMapper;
        this.applicationEventPublisher = applicationEventPublisher;
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

    @PutMapping("/updateScore/{id}")
    public MatchDto updateScore(@PathVariable("id") Long id, @RequestParam("scoreHome") Long scoreHome,
                                @RequestParam("scoreAway") Long scoreAway) {
        MatchDto result =
            matchMapper.toDto(matchService.update(id, matchMapper.fromDto(new MatchDto(scoreAway, scoreHome))));
        MatchEvent matchEvent = new MatchEvent(this, matchMapper.fromDto(result));
        applicationEventPublisher.publishEvent(matchEvent);
        return result;
    }

    @PutMapping("/updateDate/{id}")
    public MatchDto updateDate(@PathVariable("id") Long id, @RequestParam("date") String date) {
        MatchDto result =
                matchMapper.toDto(matchService.update(id, matchMapper.fromDto(new MatchDto(date))));
        return result;
    }

    @GetMapping("/test")
    public List<MatchDto> findAllByNextMatchId(Long nextMatchId) {
        return matchMapper.toDto(matchService.findAllByNextMatchId(nextMatchId));
    }
}
