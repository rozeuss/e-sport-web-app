package pl.my.e.sport.web.app.esportwebapp.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tournament")
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "public")
    @SequenceGenerator(name = "public", sequenceName = "tournament_seq", allocationSize = 1)
    private long id;

    @NotNull
    private String title;

    @NotNull
    @Size(max = 1024)
    private String description;

    @NotNull
    private String location;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    @NotNull
    private BigDecimal prize;

    public Tournament(String title, String description, String location, LocalDate startDate,
                      LocalDate endDate, BigDecimal prize) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.startDate = startDate;
        this.prize = prize;
        this.endDate = endDate;
    }
}
