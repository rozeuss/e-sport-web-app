package pl.my.e.sport.web.app.esportwebapp.domain.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import pl.my.e.sport.web.app.esportwebapp.domain.Account;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    @Mapping(target = "accountType.id", source = "accountTypeId")
    Account fromDto(AccountDto accountDto);

    @Mapping(target = "accountTypeId", source = "accountType.id")
    @Mapping(target = "password", ignore = true)
    AccountDto toDto(Account account);
}
