package pl.my.e.sport.web.app.esportwebapp.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class AccountDto {

    private long id;
    private String email;
    private String password;
    private int accountTypeId;

    public AccountDto(String email, String password, int accountTypeId) {
        this.email = email;
        this.password = password;
        this.accountTypeId = accountTypeId;
    }

    public AccountDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public AccountDto(long id, String email, String password, int accountTypeId) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.accountTypeId = accountTypeId;
    }
}
