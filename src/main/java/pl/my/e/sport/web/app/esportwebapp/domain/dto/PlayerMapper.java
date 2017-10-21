package pl.my.e.sport.web.app.esportwebapp.domain.dto;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import pl.my.e.sport.web.app.esportwebapp.domain.Player;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PlayerMapper {

    PlayerMapper INSTANCE = Mappers.getMapper(PlayerMapper.class);

    @InheritInverseConfiguration
    Player fromDto(PlayerDto playerDto);

    @Mapping(target = "teamId", source = "team.id")
    PlayerDto toDto(Player player);

    List<PlayerDto> toDto(List<Player> teams);

}
