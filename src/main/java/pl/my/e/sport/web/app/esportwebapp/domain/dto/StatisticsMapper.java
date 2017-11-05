package pl.my.e.sport.web.app.esportwebapp.domain.dto;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.my.e.sport.web.app.esportwebapp.domain.Statistics;

@Mapper(componentModel = "spring")
public interface StatisticsMapper {

    StatisticsMapper INSTANCE = Mappers.getMapper(StatisticsMapper.class);

    Statistics fromDto(StatisticsDto statisticsDto);

    StatisticsDto toDto(Statistics statistics);

}
