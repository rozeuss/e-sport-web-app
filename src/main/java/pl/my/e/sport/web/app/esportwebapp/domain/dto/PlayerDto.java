package pl.my.e.sport.web.app.esportwebapp.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PlayerDto {

    private long id;
    private String playerName;
    private String firstName;
    private String lastName;

    public PlayerDto(String playerName, String firstName, String lastName) {
        this.playerName = playerName;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
