package agent.search.repository;

import agent.search.entity.Statistic;

import java.time.LocalDate;
import java.util.List;

public interface CustomStatisticRepository {
    List<Statistic> findByCreatedAtBetween(LocalDate firstDayOfMonth, LocalDate today);
}