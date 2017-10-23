package pl.my.e.sport.web.app.esportwebapp.domain.dto;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import pl.my.e.sport.web.app.esportwebapp.domain.Team;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TeamMapper {

    TeamMapper INSTANCE = Mappers.getMapper(TeamMapper.class);

    @InheritInverseConfiguration
    Team fromDto(TeamDto teamDto);

    @Mapping(target = "accountId", source = "account.id")
    TeamDto toDto(Team team);

    List<TeamDto> toDto(List<Team> teams);
}
