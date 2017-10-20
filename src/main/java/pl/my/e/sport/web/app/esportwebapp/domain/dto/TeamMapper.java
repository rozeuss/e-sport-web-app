package pl.my.e.sport.web.app.esportwebapp.domain.dto;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.my.e.sport.web.app.esportwebapp.domain.Team;

@Mapper(componentModel = "spring")
public interface TeamMapper {

    TeamMapper INSTANCE = Mappers.getMapper(TeamMapper.class);

    Team fromDto(TeamDto playerDto);

    TeamDto toDto(Team player);
}
