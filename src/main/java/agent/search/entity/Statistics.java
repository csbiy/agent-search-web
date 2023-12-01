package agent.search.entity;


import agent.search.enumeration.StatisticProperty;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Stream;

/**
 * first-class collection
 **/
public class Statistics {

    private final List<Statistic> statistics;

    public Statistics(List<Statistic> statistics) {
        assert !CollectionUtils.isEmpty(statistics);
        this.statistics = statistics;
    }

    public List<Statistic> getTodayStatistic() {
        return statistics.stream()
                         .filter(Statistic::isToday)
                         .toList();
    }

    public List<Statistic> getMonthStatistic() {
        return Stream.of(StatisticProperty.ACTIVE_ENLIST_NUM, StatisticProperty.SUPP_ENLIST_NUM).map((property) -> {
            int sum = getSumOfProperty(property);
            return Statistic.builder()
                            .property(property)
                            .value(String.valueOf(sum))
                            .build();
        }).toList();
    }

    private int getSumOfProperty(StatisticProperty property) {
        return statistics.stream()
                         .filter(statistic -> statistic.hasProperty(property))
                         .mapToInt(statistic -> Integer.parseInt(statistic.getValue()))
                         .sum();
    }
}