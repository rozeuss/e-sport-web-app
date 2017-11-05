package pl.my.e.sport.web.app.esportwebapp.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
public class StatisticsDto {

    private long id;
    private long frags;
    private long deaths;
    private long accuracy;
    private long headshots;
    private LocalTime timePlayed;
}
