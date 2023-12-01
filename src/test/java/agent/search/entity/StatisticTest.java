package agent.search.entity;

import agent.search.enumeration.StatisticProperty;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class StatisticTest {

    private static Stream<Arguments> provideInput() {
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime yesterday = today.minusDays(1L);
        return Stream.of(
                Arguments.of(today, true),
                Arguments.of(yesterday, false)
        );
    }

    @ParameterizedTest
    @MethodSource("provideInput")
    void should_return_true_if_create_date_is_today(LocalDateTime time, boolean expectResult) {
        Statistic todayStatistic = Statistic.builder()
                                            .property(StatisticProperty.ACTIVE_ENLIST_NUM)
                                            .value("1")
                                            .createdAt(time)
                                            .build();
        assertThat(todayStatistic.isToday()).isEqualTo(expectResult);
    }
}