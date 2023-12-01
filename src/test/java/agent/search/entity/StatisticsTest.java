package agent.search.entity;

import agent.search.enumeration.StatisticProperty;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class StatisticsTest {

    @Test
    void should_return_correct_statistic() {
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime yesterday = today.minusDays(1L);

        Statistic todayActiveEnlist = createStatisticOfDateAndValue(StatisticProperty.ACTIVE_ENLIST_NUM, "1", today);
        Statistic todaySuppEnlist = createStatisticOfDateAndValue(StatisticProperty.SUPP_ENLIST_NUM, "2", today);
        Statistic yesterdayActiveEnlist = createStatisticOfDateAndValue(StatisticProperty.ACTIVE_ENLIST_NUM, "3", yesterday);
        Statistic yesterdaySuppEnlist = createStatisticOfDateAndValue(StatisticProperty.SUPP_ENLIST_NUM, "4", yesterday);

        Statistics statistics = new Statistics(List.of(todayActiveEnlist, todaySuppEnlist, yesterdayActiveEnlist, yesterdaySuppEnlist));

        List<Statistic> todayStatistic = statistics.getTodayStatistic();
        List<Statistic> monthStatistic = statistics.getMonthStatistic();

        assertThat(todayStatistic).containsExactlyInAnyOrder(todayActiveEnlist, todaySuppEnlist);
        validateMonthStatistic(monthStatistic);
    }

    private void validateMonthStatistic(List<Statistic> statistics) {
        validatePropertyValue(statistics, StatisticProperty.ACTIVE_ENLIST_NUM, "4");
        validatePropertyValue(statistics, StatisticProperty.SUPP_ENLIST_NUM, "6");
    }

    private void validatePropertyValue(List<Statistic> statistics, StatisticProperty property, String expectValue) {
        List<Statistic> sumOfProperty = statistics.stream()
                                                  .filter(statistic -> statistic.hasProperty(property))
                                                  .toList();
        assertThat(sumOfProperty).hasSize(1);
        assertThat(sumOfProperty.get(0).getValue()).isEqualTo(expectValue);
    }

    private Statistic createStatisticOfDateAndValue(StatisticProperty property, String value, LocalDateTime createdAt) {
        return Statistic.builder()
                        .property(property)
                        .value(value)
                        .createdAt(createdAt)
                        .build();
    }
}