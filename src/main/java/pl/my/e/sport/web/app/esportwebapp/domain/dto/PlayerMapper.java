package pl.my.e.sport.web.app.esportwebapp.domain.dto;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.my.e.sport.web.app.esportwebapp.domain.Player;

@Mapper
public interface PlayerMapper {

    PlayerMapper INSTANCE = Mappers.getMapper(PlayerMapper.class);

    public Player fromDto(PlayerDto playerDto);

    public PlayerDto toDto(Player player);
}
