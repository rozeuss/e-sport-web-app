package pl.my.e.sport.web.app.esportwebapp.services;

import pl.my.e.sport.web.app.esportwebapp.domain.Match;

import java.util.List;

public interface MatchService {

    List<Match> listAll();

    Match findById(Long id);

    Match save(Match match);

}
