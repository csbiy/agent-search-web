package agent.search.service;

import agent.search.dto.mapper.StatisticMapper;
import agent.search.dto.response.StatisticResponse;
import agent.search.entity.Statistic;
import agent.search.entity.Statistics;
import agent.search.enumeration.StatisticDateType;
import agent.search.repository.StatisticRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatisticService {

    private final StatisticRepository statisticRepository;

    public List<StatisticResponse> getStatistics() {
        LocalDate today = LocalDate.now();
        LocalDate firstDayOfMonth = today.withDayOfMonth(1);
        List<Statistic> foundStatistics = statisticRepository.findByCreatedAtBetween(firstDayOfMonth, today);

        if (CollectionUtils.isEmpty(foundStatistics)) {
            log.error("statistic data does not exist on date between [{}] ~ [{}]", today, firstDayOfMonth);
            return Collections.emptyList();
        }

        Statistics monthStatistics = new Statistics(foundStatistics);

        List<Statistic> todayStatistic = monthStatistics.getTodayStatistic();
        List<Statistic> monthStatistic = monthStatistics.getMonthStatistic();

        List<StatisticResponse> statisticResponses = StatisticMapper.map(todayStatistic, StatisticDateType.TODAY);
        List<StatisticResponse> monthStatisticResponse = StatisticMapper.map(monthStatistic, StatisticDateType.MONTH);
        statisticResponses.addAll(monthStatisticResponse);
        return statisticResponses;
    }

}