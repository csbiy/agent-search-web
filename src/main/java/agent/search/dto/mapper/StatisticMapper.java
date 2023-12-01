package agent.search.dto.mapper;

import agent.search.dto.response.StatisticResponse;
import agent.search.entity.Statistic;
import agent.search.enumeration.StatisticDateType;
import agent.search.enumeration.StatisticProperty;

import java.util.List;


public abstract class StatisticMapper {

    public static List<StatisticResponse> map(List<Statistic> statistics, StatisticDateType dateType) {
        return statistics.stream().map(statistic -> {
            StatisticProperty property = statistic.getProperty();
            Integer value = Integer.parseInt(statistic.getValue());
            return StatisticResponse.fromProperty(dateType, property, value);
        }).toList();
    }
}