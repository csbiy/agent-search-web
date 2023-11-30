package agent.search.service;

import agent.search.dto.mapper.StatisticMapper;
import agent.search.dto.response.StatisticResponse;
import agent.search.entity.Statistic;
import agent.search.enumeration.StatisticDateType;
import agent.search.enumeration.StatisticProperty;
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
        Statistic todayStatistic = foundStatistics.remove(0);
        StatisticResponse response = StatisticMapper.map(todayStatistic, StatisticDateType.TODAY);

        int activeEnlistSum = getSumOfProperty(foundStatistics, StatisticProperty.ACTIVE_ENLIST_NUM);
        int suppEnlistSum = getSumOfProperty(foundStatistics, StatisticProperty.ACTIVE_ENLIST_NUM);
        return null;
    }

    private int getSumOfProperty(List<Statistic> foundStatistics, StatisticProperty property) {
        return foundStatistics.stream().filter(statistic -> statistic.isSameProperty(property))
                              .mapToInt(statistic -> Integer.parseInt(statistic.getValue()))
                              .sum();
    }
}