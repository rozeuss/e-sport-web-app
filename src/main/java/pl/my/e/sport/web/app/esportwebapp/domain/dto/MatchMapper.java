package pl.my.e.sport.web.app.esportwebapp.domain.dto;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import pl.my.e.sport.web.app.esportwebapp.domain.Match;

import java.util.List;

@Mapper(componentModel = "spring", uses = TeamMapper.class)
public interface MatchMapper {

    MatchMapper INSTANCE = Mappers.getMapper(MatchMapper.class);


    @InheritInverseConfiguration
    Match fromDto(MatchDto matchDto);

    @Mapping(target = "tournamentId", source = "tournament.id")
    @Mapping(target = "nextMatchId", source = "nextMatch.id")
    MatchDto toDto(Match match);

    List<MatchDto> toDto(List<Match> matches);
}
