package pl.my.e.sport.web.app.esportwebapp.domain.dto;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import pl.my.e.sport.web.app.esportwebapp.domain.Match;
import java.util.List;

@Mapper(componentModel = "spring")
public interface MatchMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    @InheritInverseConfiguration
    Match fromDto(MatchDto matchDto);

    @Mapping(target = "tournament", source = "tournament.id")
    MatchDto toDto(Match match);

    List<MatchDto> toDto(List<Match> matches);
}
