package pl.my.e.sport.web.app.esportwebapp.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TeamDto {

    private long id;
    private String name;
    private String country;
    private long accountId;

    public TeamDto(String name, String country) {
        this.name = name;
        this.country = country;
    }
}
