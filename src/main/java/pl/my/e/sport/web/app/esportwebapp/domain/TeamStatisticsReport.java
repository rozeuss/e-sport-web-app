package pl.my.e.sport.web.app.esportwebapp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamStatisticsReport {

    Integer playedMatches;
    Integer wonMatches;
    Integer lostMatches;
    Integer firstPlaces;
    Integer secondPlaces;
    Integer thirdPlaces;

}
