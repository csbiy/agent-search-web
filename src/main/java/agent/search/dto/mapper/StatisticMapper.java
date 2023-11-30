package agent.search.dto.mapper;

import agent.search.dto.response.StatisticResponse;
import agent.search.entity.Statistic;
import agent.search.enumeration.StatisticDateType;
import agent.search.enumeration.StatisticProperty;

/**
 * Created by P-161 at : 2023-11-30
 *
 * 여기에 StatisticMapper 클래스에 대한 설명을 기술해주세요
 *
 * @author P-161
 * @version 1.0
 * @since 1.0
 */
public abstract class StatisticMapper {

    public static StatisticResponse map(Statistic statistic, StatisticDateType dateType) {
        StatisticProperty property = statistic.getProperty();
        Integer value = Integer.parseInt(statistic.getValue());
        return StatisticResponse.fromProperty(dateType, property, value);
    }
}