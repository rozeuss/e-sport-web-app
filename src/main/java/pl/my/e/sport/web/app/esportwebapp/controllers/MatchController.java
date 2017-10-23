package pl.my.e.sport.web.app.esportwebapp.controllers;

import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.my.e.sport.web.app.esportwebapp.domain.dto.MatchDto;
import pl.my.e.sport.web.app.esportwebapp.domain.dto.MatchMapper;
import pl.my.e.sport.web.app.esportwebapp.domain.dto.PlayerDto;
import pl.my.e.sport.web.app.esportwebapp.services.MatchService;

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
    @RequestMapping(value = "/sayHello", method = RequestMethod.GET)
    public String sayHello() {
        //TODO do usuniecia
        log.info(StringUtils.isEmpty("dwa"));
        log.error("strzala");
        return "hello world";
    }

    @PostMapping("/create")
    public MatchDto create(@RequestParam("tournament") long tournamentId,
                           @RequestParam("phase") int phase) {
        MatchDto matchDto = new MatchDto(tournamentId, phase);
        return MatchMapper.toDto(matchService.create(matchMapper.fromDto(matchDto)));
    }

    @PutMapping("/close")
    public boolean closeMatch(@RequestParam("id") long matchId) {

        return false;
    }
}
