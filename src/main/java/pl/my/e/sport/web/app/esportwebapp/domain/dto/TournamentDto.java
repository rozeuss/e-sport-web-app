package pl.my.e.sport.web.app.esportwebapp.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class TournamentDto {

    // adnotacja uniemozliwia updatowanie po podaniu ID przy create
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private long id;
    private String title;
    private String description;
    private String location;
    private String startDate;
    private String endDate;
    private BigDecimal prize;

    public TournamentDto(long id, String title, String description,
                         String location, String startDate, String endDate, BigDecimal prize) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.prize = prize;
    }
}
