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
        // TODO : 일급 컬렉션을 사용하도록 리팩토링
        getFirstOfProperty(foundStatistics, StatisticProperty.ACTIVE_ENLIST_NUM);
        getFirstOfProperty(foundStatistics, StatisticProperty.SUPP_ENLIST_NUM);

        int suppEnlistSum = getSumOfProperty(foundStatistics, StatisticProperty.ACTIVE_ENLIST_NUM);
        int activeEnlistSum = getSumOfProperty(foundStatistics, StatisticProperty.SUPP_ENLIST_NUM);
        StatisticResponse activeEnlistResponse = StatisticResponse.fromProperty(StatisticDateType.MONTH, StatisticProperty.ACTIVE_ENLIST_NUM, activeEnlistSum);
        StatisticResponse suppEnlistResponse = StatisticResponse.fromProperty(StatisticDateType.MONTH, StatisticProperty.SUPP_ENLIST_NUM, suppEnlistSum);

        return List.of(response,activeEnlistResponse,suppEnlistResponse);
    }

    private int getSumOfProperty(List<Statistic> foundStatistics, StatisticProperty property) {
        return foundStatistics.stream().filter(statistic -> statistic.isSameProperty(property))
                              .mapToInt(statistic -> Integer.parseInt(statistic.getValue()))
                              .sum();
    }

    private int getFirstOfProperty(List<Statistic> foundStatistics, StatisticProperty property) {
        return foundStatistics.stream()
                .filter(statistic -> statistic.isSameProperty(property))
                .findFirst()
                .stream()
                .mapToInt(statistic ->
                    Integer.parseInt(statistic.getValue()))
                .findFirst()
                .getAsInt();
    }
}