package pl.my.e.sport.web.app.esportwebapp.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PlayerDto {

    private String login;
    private String playerName;
    private String firstName;
    private String lastName;

    public PlayerDto(String login, String playerName, String firstName, String lastName) {
        this.login = login;
        this.playerName = playerName;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
