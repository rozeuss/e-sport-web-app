package pl.my.e.sport.web.app.esportwebapp.domain.dto;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import pl.my.e.sport.web.app.esportwebapp.domain.Tournament;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Mapper(componentModel = "spring")
public interface TournamentMapper {


    String LOCAL_DATE_FORMAT = "dd.MM.yyyy";

    TournamentMapper INSTANCE = Mappers.getMapper(TournamentMapper.class);

    @InheritInverseConfiguration
    Tournament fromDto(TournamentDto tournamentDto);

    @Mapping(target = "organizerId", source = "organizer.id")
    TournamentDto toDto(Tournament tournament);

    List<TournamentDto> toDto(List<Tournament> tournaments);

    default Date map(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    default LocalDate map(Date localDate) {
        return localDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

}
