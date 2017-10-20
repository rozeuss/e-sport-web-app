package pl.my.e.sport.web.app.esportwebapp.domain.dto;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.my.e.sport.web.app.esportwebapp.domain.Player;

@Mapper(componentModel = "spring")
public interface PlayerMapper {

    PlayerMapper INSTANCE = Mappers.getMapper(PlayerMapper.class);

    Player fromDto(PlayerDto playerDto);

    PlayerDto toDto(Player player);
}
